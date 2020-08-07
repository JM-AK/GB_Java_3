package ru.geekbrains.p2_synch_counter;


public class SynchCounter {
    Object lock1 = new Object();
    Object lock2 = new Object();
    int c;
    int c2;

    public int value() { return c; }

    public SynchCounter() {
        c = 0; c2 = 0;
    }

    public void inc() {
        synchronized (lock1) {
            c++;
        }
    }

    public void dec() {
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
