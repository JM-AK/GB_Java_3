package ru.geekbrains.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainClass {

    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);
        Integer i = 0;

        if (ai.getAndSet(ai.get() + 1) < 10)  {
            System.out.println("asds");
        }

        AtomicBoolean ab = new AtomicBoolean(false);
        if (!ab.getAndSet(true)) {
            System.out.println("Only one thread can get it");
        }

    }

    private static void reentrantLockExample() {
        ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();
        rrwl.readLock().lock();
        rrwl.readLock().unlock();

        rrwl.writeLock().lock();
        rrwl.writeLock().unlock();
    }

    private static void lockExample() {
        ReentrantLock lock = new ReentrantLock();
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                //...
            } else {
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void cyclicBarrierExample() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " Start");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " Prepared");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " Run");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " Start");
                    Thread.sleep(4000);
                    System.out.println(Thread.currentThread().getName() + " Prepared");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " Run");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " Start");
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " Prepared");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " Run");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void countDownLatch() throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(3);
        for (int i = 0; i < 3 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("Sub Start");
                        Thread.sleep(4000);
                        cdl.countDown();
                        System.out.println("Sub Finish");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        cdl.await();
        System.out.println("Main Finish");
    }

    private static void semaphoreExample() {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        Thread.sleep(1000);
                        System.out.println("processed ");
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void concurrentCollecitons() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(500);
                        queue.put("A");
                        System.out.println("A");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1500);
                        System.out.println(queue.take() + " cons");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        // Запись
        // queue.put(); - если нет места - переходит в wait
        // queue.add(); - если нет места - вернется Exception
        // queue.offer(); если получилось положить в очередь - true, если нет - false

        // Чтение
        // queue.take(); - если нет элементов - переходит в wait
        // queue.poll(); - если элемента нет - null, если есть - наш объект
        // queue.peek(); - просмотр элемента, не забирает из очереди


        //CopyOnWriteArrayList<Integer> copyOnWriteArrayList;

        //Collections.synchronizedMap() - Оборачивание коллекции в синтетическую с synchronized методами
    }

    private static void executorServiceExample() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "processed";
            }
        });
        String str = future.get();
        System.out.println(str);
        executorService.shutdown();


        //ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        //executorService.scheduleAtFixedRate() // запуск без учета времени работы треда
        //executorService.scheduleWithFixedDelay() // запуск с учетом времени работы треда
    }

}
