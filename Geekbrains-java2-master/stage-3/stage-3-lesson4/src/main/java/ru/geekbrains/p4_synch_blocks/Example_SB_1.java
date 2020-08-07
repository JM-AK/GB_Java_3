package ru.geekbrains.p4_synch_blocks;

public class Example_SB_1 {
    public static void main(String[] args) {
        Example_SB_1 e1 = new Example_SB_1();
        Example_SB_1 e2 = new Example_SB_1();
        System.out.println("Start");
        new Thread(() -> e1.method1()).start();
        new Thread(() -> e2.method1()).start();
    }

    public synchronized void method1() {
        System.out.println("M1.1 " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M1.2 " + Thread.currentThread().getName());
    }

    public synchronized void method2() {
        System.out.println("M2.1 " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M2.2 " + Thread.currentThread().getName());
    }

    public void method3() {
        System.out.println("M3.1 " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M3.2 " + Thread.currentThread().getName());
    }
}
