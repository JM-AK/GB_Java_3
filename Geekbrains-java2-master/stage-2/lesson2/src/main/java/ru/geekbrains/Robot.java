package ru.geekbrains;

public class Robot implements RobotInt {


    @Override
    public void walk() {
        RobotInt.super.walk();
        System.out.println("....");
    }

    @Override
    public void think() {

    }
}
