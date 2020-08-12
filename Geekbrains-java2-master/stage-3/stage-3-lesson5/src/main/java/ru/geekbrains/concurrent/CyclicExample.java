package ru.geekbrains.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicExample {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3);
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " Thread preparing");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " Thread prepared");
                cb.await();
                System.out.println(Thread.currentThread().getName() + " Thread run!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " Thread preparing");
                Thread.sleep(3500);
                System.out.println(Thread.currentThread().getName() + " Thread prepared");
                cb.await();
                System.out.println(Thread.currentThread().getName() + " Thread run!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " Thread preparing");
                Thread.sleep(2500);
                System.out.println(Thread.currentThread().getName() + " Thread prepared");
                cb.await();
                System.out.println(Thread.currentThread().getName() + " Thread run!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
