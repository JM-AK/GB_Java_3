package ru.geekbrains;

public class ThreadTest {

    public static void main(String[] args) {
        /// Подготовка в однопотоке
        // ....

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A");
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B");
            }
        });
        t2.start();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("C");
            }
        });
        t3.start();

        try {
            System.out.println("to t1.join");
            t1.join();
            System.out.println("to t2.join");
            t2.join();
            System.out.println("to t3.join");
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Склейка

        System.out.println("D");
    }

}
