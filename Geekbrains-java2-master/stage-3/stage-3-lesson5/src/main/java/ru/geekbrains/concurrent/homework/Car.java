package ru.geekbrains.concurrent.homework;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable{
    private static int CAR_NUMBER;
    static {
        CAR_NUMBER = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    private CyclicBarrier cb;
    private CountDownLatch cdl;

    public Car(Race race, int speed, CyclicBarrier cb, CountDownLatch cdl) {
        this.race = race;
        this.speed = speed;
        CAR_NUMBER++;
        this.name = "Участник #" + CAR_NUMBER;
        this.cb = cb;
        this.cdl = cdl;
    }
    @Override
    public void run() {

        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cb.await();
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if (!race.isWinnerExists().getAndSet(true)) {
            System.out.println(this.name + " - WIN");
        }
        cdl.countDown();
    }


}
