package ru.geekbrains;

public class Monitor {

    private static long a;
    private static long b;
    private static long c;
    private static final Object monitor = new Object();

    private synchronized static void calc () {
        synchronized (monitor) {
            for (int i = 0; i < 1_000_000; i++) {
                a = a + 1;
                b = b + 1;
                c = c + 1;
            }
            String result = String.format("a=%d, b=%d, c=%d", a, b, c);
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                calc();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                calc();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                calc();
            }
        }).start();
    }

}
