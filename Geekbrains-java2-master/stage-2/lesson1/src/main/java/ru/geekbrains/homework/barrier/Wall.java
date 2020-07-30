package ru.geekbrains.homework.barrier;

public class Wall implements Barrier {

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public int getSize() {
        return height;
    }

}
