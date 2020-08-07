package ru.geekbrains.p4_synch_blocks;

public class Example_SB_2 {
    private Object lock1 = new Object();

    public static void main(String[] args) {
        Example_SB_2 e2 = new Example_SB_2();
        System.out.println("Start");
        new Thread(() -> e2.method1()).start();
        new Thread(() -> e2.method1()).start();
    }

    public void method1() {
        System.out.println("NonSynch begin " + Thread.currentThread().getName());
        for (int i = 0; i < 3; i++) {
            System.out.println(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("NonSynch end " + Thread.currentThread().getName());
        synchronized (lock1) {
            System.out.println("Synch block begin " + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Synch block end " + Thread.currentThread().getName());
        }
        //
        System.out.println("M2");
    }
}
