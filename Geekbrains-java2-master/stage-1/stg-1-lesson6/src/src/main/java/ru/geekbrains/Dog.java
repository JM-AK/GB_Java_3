package ru.geekbrains;

public class Dog extends Animal {

    public Dog(String name, String color, int age) {
        super(name, color, age);
    }

    public void voice() {
        System.out.println(name + " bark");
    }

}
