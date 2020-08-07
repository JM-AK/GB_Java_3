package ru.geekbrains.p8_wait_and_notify;

public class DataManager {
    private final Object monitor = new Object();

    public DataManager() {

    }

    public void sendData() {
         synchronized (monitor) {
            try {
                System.out.println("Sending data ...");
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void prepareData() {
         synchronized (monitor) {
             System.out.println("Data prepared");
            monitor.notify();
        }
    }

    public static void main(String[] args) {
        DataManager dm = new DataManager();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    dm.prepareData();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 9; i++) {
                    dm.sendData();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
