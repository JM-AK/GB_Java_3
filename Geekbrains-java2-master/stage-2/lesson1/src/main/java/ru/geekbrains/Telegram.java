package ru.geekbrains;

public class Telegram extends Application implements Messenger {

    public Telegram() {
        this.name = "Telegram";
        this.version = "1.0";
    }

    @Override
    public String prepareMessage() {
     return "";
    }

    @Override
    public void processMessage() {

    }

}
