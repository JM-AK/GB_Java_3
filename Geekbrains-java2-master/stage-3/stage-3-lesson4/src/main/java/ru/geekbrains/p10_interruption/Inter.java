package ru.geekbrains.p10_interruption;

public class Inter implements Runnable {
    private volatile boolean running = true;

    public void terminate() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep((long) 15000);
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }
}
