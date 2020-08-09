package ru.geekbrains.homework;

public class MyThreadTest {
    private final Object monitor = new Object();
    private static volatile String currentOperation = "C";

    public void sendA() {
        try {
            synchronized (monitor) {
                while (!currentOperation.equals("C")) {
                    monitor.wait();
                }
                System.out.print("A");
                currentOperation = "A";
                monitor.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendB() {
        synchronized (monitor) {
            try {
                while (!currentOperation.equals("A")) {
                    monitor.wait();
                }
                System.out.print("B");
                currentOperation = "B";
                monitor.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendC() {
        synchronized (monitor) {
            try {
                while (!currentOperation.equals("B")) {
                    monitor.wait();
                }
                System.out.print("C");
                currentOperation = "C";
                monitor.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThreadTest threadTest = new MyThreadTest();
        Thread tA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    threadTest.sendA();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread tB = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    threadTest.sendB();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread tC = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    threadTest.sendC();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        tA.start();
        tB.start();
        tC.start();
    }

}
