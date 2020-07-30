package ru.geekbrains;

public class Cat extends Animal{

    public static int id;
    public Animal animal;

    public Cat(String name, String color, int age) {
        super(name, color, age);
        animal = this;
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

