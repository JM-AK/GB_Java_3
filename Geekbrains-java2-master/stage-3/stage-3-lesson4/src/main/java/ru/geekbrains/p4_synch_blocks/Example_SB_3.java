package ru.geekbrains.p4_synch_blocks;

public class Example_SB_3 {

    private static Object monitor = new Object();

    public static void main(String[] args) {
        System.out.println("Start");
        new Thread(() -> Example_SB_3.method()).start();
        new Thread(() -> method()).start();
    }

    public synchronized static void method() {
        System.out.println("Static method begin " + Thread.currentThread().getName());
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            try {
                synchronized (monitor) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Static method end " + Thread.currentThread().getName());
    }

    public synchronized void method2() {
        System.out.println("Static method2 begin " + Thread.currentThread().getName());
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Static method2 end " + Thread.currentThread().getName());
    }
}
