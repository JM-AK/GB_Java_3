package ru.geekbrains;

public interface Human {

    default void walk() {
        System.out.println("walk on legs");
    }

    void think();

    void something();

}
