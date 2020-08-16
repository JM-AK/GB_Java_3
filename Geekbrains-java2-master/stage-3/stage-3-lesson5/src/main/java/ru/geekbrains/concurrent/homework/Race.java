package ru.geekbrains.concurrent.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class Race {
    private AtomicBoolean isWinnerExists = new AtomicBoolean(false);
    private ArrayList<Stage> stages;

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public AtomicBoolean isWinnerExists() {
        return isWinnerExists;
    }
}