package ru.geekbrains.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) {
        // Входящие данные (к нам)
        // InputStream

        // Исходящие данные (от нас)
        // OutputStream
        try (ServerSocket serverSocket = new ServerSocket(8181)) {
            System.out.println("Server started");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String s = in.readUTF();
                System.out.println(s);
                out.writeUTF("echo: " + s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
