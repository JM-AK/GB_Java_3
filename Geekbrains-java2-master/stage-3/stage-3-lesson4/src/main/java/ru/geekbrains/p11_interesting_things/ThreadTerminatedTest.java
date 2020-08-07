package ru.geekbrains.p11_interesting_things;

public class ThreadTerminatedTest {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2");
            }
        });
        t.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(t.getState());
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
