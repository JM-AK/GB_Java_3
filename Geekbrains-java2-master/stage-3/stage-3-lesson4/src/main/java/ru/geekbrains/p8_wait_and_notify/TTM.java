package ru.geekbrains.p8_wait_and_notify;

public class TTM {
    private static final Object mon = new Object();

    public static void main(String[] args) {
        new Thread(() -> { // t1
            synchronized (mon) {
                try {
                    System.out.println("p1-1"); // выполнили
                    mon.wait(); // t1 уснул
                    System.out.println("p1-2"); // выполнили
                    mon.notify(); //
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> { // t2
            synchronized (mon) {
                try {
                    System.out.println("p2-1"); // выполнили
                    mon.notify(); // разбудили t1
                    System.out.println("p2-2"); // выполнили
                    mon.wait(); // уснул t2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
