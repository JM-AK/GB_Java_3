package ru.gb.lesson5;


public class MainClass {

    public static void main(String[] args) {
        Cat cat1 = new Cat("Barsik", 2018, null);
//        cat1.name = "Barsik";
//        cat1.age = 1;
//        cat1.color = "Brown";
//        cat1.gender = "M";

        Cat cat2 = new Cat("Murka", 2010, "Grey");
        cat2.setName("Mrk");

        System.out.println("===============");
        System.out.println("Cat: " + cat1 + " of " + cat1.getName() + " of age " + cat1.getAge());
        System.out.println("Cat: " + cat2 + " of " +  cat2.getName() + " of age " + cat2.getAge());
        System.out.println("===============");
        cat1 = cat2;

        System.out.println("Cat: " + cat1 + " of " + cat1.getName() + " of age " + cat1.getAge());
        System.out.println("Cat: " + cat2 + " of " +  cat2.getName() + " of age " + cat2.getAge());
        System.out.println("===============");

//        System.out.println("Cat: " + cat1.name + " of age " + cat1.age + " is " + cat1.gender + ", and color " + cat1.color);
//        System.out.println("Cat: " + cat2.name + " of age " + cat2.age + " is " + cat2.gender + ", and color " + cat2.color);

        System.out.println("Cat: " + cat1.getName() + " of age " + cat1.getAge());
        cat1.voice();
        cat2.jump();

    }

    public void call() {

    }

}
