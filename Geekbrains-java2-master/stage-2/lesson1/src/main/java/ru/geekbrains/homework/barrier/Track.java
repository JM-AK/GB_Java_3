package ru.geekbrains.homework.barrier;

public class Track implements Barrier {

    private int length;

    public Track(int length) {
        this.length = length;
    }

    @Override
    public int getSize() {
        return length;
    }
}
