package ru.geekbrains;

public class MessageSender {

    public void sendMessage(Messenger messenger) {
        String message = messenger.prepareMessage();
        send(message);
    }

    public void send(String message) {

    }

}
