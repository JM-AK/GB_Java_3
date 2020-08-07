package ru.geekbrains.p8_wait_and_notify;

public class DataManagerProcessing {
    private final Object monitor = new Object();
    private static volatile String currentOperation = "prepare";

    public void sendData() {
        try {
            synchronized (monitor) {
                while (!currentOperation.equals("send")) {
                    monitor.wait();
                }
                System.out.println("Sending data");
                currentOperation = "process";
                monitor.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void prepareData() {
        synchronized (monitor) {
            try {
                while (!currentOperation.equals("prepare")) {
                    monitor.wait();
                }
                System.out.println("Data prepared");
                currentOperation = "send";
                monitor.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void processData() {
        synchronized (monitor) {
            try {
                while (!currentOperation.equals("process")) {
                    monitor.wait();
                }
                System.out.println("Process data");
                currentOperation = "prepare";
                monitor.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DataManagerProcessing dm = new DataManagerProcessing();
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

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 9; i++) {
                    dm.processData();
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
        t3.start();
    }
}
