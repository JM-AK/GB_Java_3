package ru.geekbrains;

public class Bird extends Animal {

    int flyHeight;

    public Bird(String name, String color, int age, int flyHeight) {
        super(name, color, age);
        this.flyHeight = flyHeight;
    }


    public void fly() {
        System.out.println(name + "fly");
    }

    public void voice() {
        System.out.println(name + " tweet");
    }

}
