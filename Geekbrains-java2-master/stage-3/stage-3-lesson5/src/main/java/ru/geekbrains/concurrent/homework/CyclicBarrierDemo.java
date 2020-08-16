package ru.geekbrains.concurrent.homework;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(4);
        for (int i = 0; i < 4; i++) {
            final int w = i;
            new Thread(() -> {
                try{
                    System.out.println("Участник " + w + " готовится");
                    Thread.sleep(500 + (int)(Math.random() * 800));
                    System.out.println("Участник " + w + " готов");
                    cb.await();
                    System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

