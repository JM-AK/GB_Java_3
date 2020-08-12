package ru.geekbrains;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import static ru.geekbrains.MainClass.CARS_COUNT;

public class Car implements Runnable {
    static CyclicBarrier cbReady = new CyclicBarrier(CARS_COUNT);
    static final CountDownLatch cdFinish = new CountDownLatch(CARS_COUNT);
    static CountDownLatch cdStart = new CountDownLatch(CARS_COUNT);

    private static int carsNum;
    static {
        carsNum = 0;
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
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        carsNum++;
        this.name = "Участник #" + carsNum;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cbReady.await();

        } catch (Exception e) {
            e.printStackTrace();
        }

        cdStart.countDown();

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        cdFinish.countDown();
    }
}
