package ru.geekbrains;

// anstract на уровне класса - запрет создания экземпляров
public abstract class Animal extends Object {

    public static int id;
    public String name;
    public String color;
    public int age;
    public Coordinates coordinates;


    public Animal(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    // anstract на уровне метода - требование к обязательной реализации у потомка
    public abstract void voice();

    public void move() {
        System.out.println(name + " walks on paws");
    }

    public class Coordinates{
        int weight;
        int height;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                ", coordinates=" + coordinates +
                '}';
    }
}
