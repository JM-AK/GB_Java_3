package ru.geekbrains;

public class Cat extends Animal{

    public static int id;

    public Cat(String name, String color, int age) {
        super(name, color, age);
    }

    public void voice() {
        System.out.println(name + " meow");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                ", coordinates=" + coordinates +
                '}';
    }
}

