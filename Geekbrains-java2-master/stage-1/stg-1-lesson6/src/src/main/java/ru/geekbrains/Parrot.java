package ru.geekbrains;


// super - родитель Bird
// this - текущий класс Parrot
public class Parrot extends Bird {

    public Parrot(String name, String color, int age, int flyHeight) {
       super(name, color, age, flyHeight);
    }


    public void speak() {
        System.out.println(name + " speaks");
    }

    public void voice() {
        System.out.println(name + " tweet");
    }
}
