package ru.geekbrains.homework.participant;

import ru.geekbrains.homework.barrier.Barrier;
import ru.geekbrains.homework.barrier.Track;
import ru.geekbrains.homework.barrier.Wall;

public interface Movable {

    boolean walk(Track track);

    boolean jump(Wall wall);

    boolean overcome(Barrier barrier);

    boolean isDroppedOut();

    void setDroppedOut(boolean value);

}
