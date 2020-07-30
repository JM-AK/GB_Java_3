package ru.geekbrains;

public class Main2 {

    static class Animal {
        public void voice() {
            System.out.println("1");
        }
    }

    static class Cat extends Animal{

        public Cat() {
        }

        @Override
        public void voice() {
            System.out.println("meow");
        }
    }

    static class Dog extends Animal {

        public int age;

        public Dog(int age) {
            this.age = age;
        }

        public void voice() {
            System.out.println("bark");
        }

        public void jump() {

        }

        @Override
        public boolean equals(Object another) {
            if (this == another) {
                return true;
            }
            if (another == null || getClass() != another.getClass()){
                return false;
            }
            Dog dog = (Dog)another;
            return this.age == dog.age;
        }
    }

}
