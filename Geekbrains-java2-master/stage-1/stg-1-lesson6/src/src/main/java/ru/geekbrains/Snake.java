package ru.geekbrains;

public class Snake extends Animal {

    public Snake(String name, String color, int age) {
        super(name, color, age);
    }

    @Override
    public void voice() {
        System.out.println(name + " shshshshshs");
    }

    @Override
    public void move() {
        System.out.println(name + " crawls");
    }
}
