package ru.geekbrains.ref;

public class Cat {

    private int id;
    String name;
    public int age;

    public Cat(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @MyAnno(val = 10)
    public void info() {
        System.out.println(id + " " + name + " " + age);
    }

    public void info(int i) {
        System.out.println(id + " ii " + name + " " + age);
    }

    @MyAnno(val = 3)
    public void callMe() {
        System.out.println("Meow!");
    }

    private void meow() {
        System.out.println("Meow!");
    }

}
