package ru.geekbrains.p1_base;

public class Example_Base_2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /*
          Признак приоритета, говорит о том, что потенциально поток с высоким приоритетом может получить больше процессорного времени
         */
        t1.setPriority(7);
        t1.setName("ForThread");
        /*
          Признак Daemon-потока, который работает пока есть хоть один рабочий поток
         */
        t1.setDaemon(true);
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main -> stop");
    }
}
