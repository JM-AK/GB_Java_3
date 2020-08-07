package ru.geekbrains.p12_executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(7);
//        executorService = Executors.newSingleThreadExecutor();
//        executorService = Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(() -> System.out.println("success"), 1000, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("success"), 10, 10, TimeUnit.MINUTES);
        //scheduledExecutorService.scheduleWithFixedDelay()

        executorService.execute(() -> System.out.println("success"));
        executorService.execute(() -> System.out.println("success2"));

        executorService.shutdown();
        executorService.shutdownNow();
//        executorService.awaitTermination(10, TimeUnit.DAYS);
        Thread.yield();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean stopProcessing = false;
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("sleep");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
//                        stopProcessing = true;
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        t.start();

        try {
            Thread.sleep(1000);
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
