package ru.geekbrains.homework.participant;

import ru.geekbrains.homework.barrier.Barrier;
import ru.geekbrains.homework.barrier.Track;
import ru.geekbrains.homework.barrier.Wall;

public class Human implements Movable{

    private String name;
    private int maxJumpHeight;
    private int maxWalkLength;
    private boolean droppedOut;

    public Human(String name, int maxJumpHeight, int maxWalkLength) {
        this.name = name;
        this.maxJumpHeight = maxJumpHeight;
        this.maxWalkLength = maxWalkLength;
    }

    @Override
    public boolean walk(Track track) {
        return maxWalkLength > track.getSize();
    }

    @Override
    public boolean jump(Wall wall) {
        return maxJumpHeight > wall.getSize();
    }

    @Override
    public boolean overcome(Barrier barrier) {
       return maxJumpHeight + maxWalkLength  > barrier.getSize();
    }

    @Override
    public boolean isDroppedOut() {
        return droppedOut;
    }

    @Override
    public void setDroppedOut(boolean value) {
        this.droppedOut = value;
    }

    public String toString() {
        return name;
    }
}
