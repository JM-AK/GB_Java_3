package ru.geekbrains.tests;

public class Calculator {

    public int add (int x, int y) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return x + y;
    }

    public int multiply (int x, int y) {
        return x * y;
    }

    public int div (int x, int y) {
        return x / y;
    }
}
