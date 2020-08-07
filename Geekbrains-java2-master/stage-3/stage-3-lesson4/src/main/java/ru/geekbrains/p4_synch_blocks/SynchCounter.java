package ru.geekbrains.p4_synch_blocks;


public class SynchCounter {
    Object lock1 = new Object();
    Object lock2 = new Object();
    int c;
    int c2;

    public int value() { return c; }

    public SynchCounter() {
        c = 0; c2 = 0;
    }

    public  void inc() {
        synchronized (lock1) {
            c++;
        }
    }

    public synchronized void dec() {
        synchronized (lock1) {
            c--;
        }
    }

    public int value2() { return c2; }


    public synchronized void inc2() {
        synchronized (lock2) {
            c2++;
        }
    }

    public synchronized void dec2() {
        synchronized (lock2) {
            c2--;
        }
    }
}
