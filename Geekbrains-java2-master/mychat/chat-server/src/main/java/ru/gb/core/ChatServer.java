package ru.gb.core;

import ru.gb.chat.common.MessageLibrary;
import ru.gb.net.MessageSocketThread;
import ru.gb.net.MessageSocketThreadListener;
import ru.gb.net.ServerSocketThread;
import ru.gb.net.ServerSocketThreadListener;

import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer implements ServerSocketThreadListener, MessageSocketThreadListener {
    private static final int THREAD_CAPABILITY = 100;
    private ServerSocketThread serverSocketThread;
    private ru.gb.core.ChatServerListener listener;
    private AuthController authController;
    private Vector<ru.gb.core.ClientSessionThread> clients = new Vector<>();
    private ExecutorService poolClients = Executors.newFixedThreadPool(THREAD_CAPABILITY);

    public ChatServer(ChatServerListener listener) {
        this.listener = listener;
    }

    public void start(int port) {
        if (serverSocketThread != null && serverSocketThread.isAlive()) {
            return;
        }
        serverSocketThread = new ServerSocketThread(this,"Chat-Server-Socket-Thread", port, 2000);
        serverSocketThread.start();
        authController = new AuthController();
        authController.init();
    }

    public void stop() {
        if (serverSocketThread == null || !serverSocketThread.isAlive()) {
            return;
        }
        serverSocketThread.interrupt();
        disconnectAll();
    }

    /*
     * Server Socket Thread Listener Methods
     */

    @Override
    public void onClientConnected() {
        logMessage("Client connected");
    }

    @Override
    public void onSocketAccepted(Socket socket) {
        clients.add(new ru.gb.core.ClientSessionThread(this, "ClientSessionThread", socket));
    }

    @Override
    public void onException(Throwable throwable) {
        throwable.printStackTrace();
    }

    /*
     * Message Socket Thread Listener Methods
     */

    @Override
    public void onClientTimeout(Throwable throwable) {
    }

    @Override
    public void onSocketReady(MessageSocketThread thread) {
        logMessage("Socket ready");
    }

    @Override
    public void onSocketClosed(MessageSocketThread thread) {
        ru.gb.core.ClientSessionThread clientSession = (ru.gb.core.ClientSessionThread) thread;
        logMessage("Socket Closed");
        clients.remove(thread);
        if (clientSession.isAuthorized() && !clientSession.isReconnected()) {
            sendToAllAuthorizedClients(MessageLibrary.getBroadcastMessage("server", "User " + clientSession.getNickname() + " disconnected"));
        }
        sendToAllAuthorizedClients(MessageLibrary.getUserList(getUsersList()));
    }

    @Override
    public void onMessageReceived(MessageSocketThread thread, String msg) {
        ru.gb.core.ClientSessionThread clientSession = (ru.gb.core.ClientSessionThread)thread;
        if (clientSession.isAuthorized()) {
            processAuthorizedUserMessage(clientSession, msg);
        } else {
            processUnauthorizedUserMessage(clientSession, msg);
        }
    }

    @Override
    public void onException(MessageSocketThread thread, Throwable throwable) {
        throwable.printStackTrace();
    }

    private void processAuthorizedUserMessage(ru.gb.core.ClientSessionThread clientSession, String msg) {
        logMessage(msg);
        String[] arr = msg.split(MessageLibrary.DELIMITER);
        if (arr[0].equals(MessageLibrary.AUTH_METHOD) && arr[1].equals(MessageLibrary.AUTH_UPDATE)){
            String login = arr[2];
            String password = arr[3];
            String nickname = arr[4];
            String oldNickname = authController.getNickname(login, password);

            ru.gb.core.ClientSessionThread oldClientSession = findClientSessionByNickname(oldNickname);
            if(oldClientSession != null) clients.remove(oldClientSession);
            clients.add(clientSession);
//            oldClientSession.setReconnected(true);

            try {
                ChatDB.connect();
                ChatDB.updateNickname(login, nickname);
                System.out.println("Chat DB updated");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                ChatDB.disconnect();
            }

            clientSession.setNickname(nickname);
            clientSession.authAccept(nickname);

            sendToAllAuthorizedClients(MessageLibrary.getBroadcastMessage("Server", nickname + " reconnected"));
            sendToAllAuthorizedClients(MessageLibrary.getUserList(getUsersList()));

        }else {
            for (ru.gb.core.ClientSessionThread client : clients) {
                if (!client.isAuthorized()) {
                    continue;
                }
                client.sendMessage(msg);
            }
        }
    }

    private void sendToAllAuthorizedClients(String msg) {
        for (ru.gb.core.ClientSessionThread client : clients) {
            if(!client.isAuthorized()) {
                continue;
            }
            client.sendMessage(msg);
        }
    }

    private void processUnauthorizedUserMessage(ru.gb.core.ClientSessionThread clientSession, String msg) {
        String[] arr = msg.split(MessageLibrary.DELIMITER);
        if (arr.length < 4 ||
                !arr[0].equals(MessageLibrary.AUTH_METHOD) ||
                !arr[1].equals(MessageLibrary.AUTH_REQUEST)) {
            clientSession.authError("Incorrect request: " + msg);
            return;
        }
        String login = arr[2];
        String password = arr[3];
        String nickname = authController.getNickname(login, password);
        if (nickname == null) {
            clientSession.authDeny();
            return;
        } else {
            ru.gb.core.ClientSessionThread oldClientSession = findClientSessionByNickname(nickname);
            clientSession.authAccept(nickname);
            if (oldClientSession == null) {
                sendToAllAuthorizedClients(MessageLibrary.getBroadcastMessage("Server", nickname + " connected"));
            } else {
                oldClientSession.setReconnected(true);
                clients.remove(oldClientSession);
            }
        }
        sendToAllAuthorizedClients(MessageLibrary.getUserList(getUsersList()));
     }

    public void disconnectAll() {
        ArrayList<ru.gb.core.ClientSessionThread> currentClients = new ArrayList<>(clients);
        for (ru.gb.core.ClientSessionThread client : currentClients) {
            client.close();
            clients.remove(client);
        }

    }

    private void logMessage(String msg) {
        listener.onChatServerMessage(msg);
    }

    public String getUsersList() {
        StringBuilder sb = new StringBuilder();
        for (ru.gb.core.ClientSessionThread client : clients) {
            if (!client.isAuthorized()) {
                continue;
            }
            sb.append(client.getNickname()).append(MessageLibrary.DELIMITER);
        }
        return sb.toString();
    }

    private ru.gb.core.ClientSessionThread findClientSessionByNickname(String nickname) {
        for (ClientSessionThread client : clients) {
            if (!client.isAuthorized()) {
                continue;
            }
            if (client.getNickname().equals(nickname)) {
                return client;
            }
        }
        return null;
    }
}
