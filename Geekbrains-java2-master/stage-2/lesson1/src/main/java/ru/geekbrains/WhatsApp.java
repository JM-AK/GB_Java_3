package ru.geekbrains;

public class WhatsApp extends Application implements Messenger{

    public WhatsApp() {
        this.name = "WhatsApp";
        this.version = "1.0";
    }

    public String prepareMessage() {
        return "";
    }

    @Override
    public void processMessage() {

    }
}
