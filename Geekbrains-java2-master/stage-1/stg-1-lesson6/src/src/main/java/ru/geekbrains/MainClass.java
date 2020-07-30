package ru.geekbrains;

public class MainClass {

    public static void main(String[] args) {

        Cat cat = new Cat("Barsik", "Black", 3);   // Cat@1
        Dog dog = new Dog("Jack", "White", 5);
        cat.equals(dog);
        Bird bird = new Bird("Kesha1", "Red", 2, 10);
        Parrot parrot = new Parrot("Kesha2", "Red", 2, 10);
        Snake snake = new Snake("Kaa", "Brown", 1);

        String var = null;

//        Animal.Coordinates co = new Animal("Kaa", "Brown", 1).new Coordinates(); 


        System.out.println("==================");
        System.out.println("Animal: " + cat.name + " of color " + cat.color);
        System.out.println("Animal: " + dog.name + " of color " + dog.color);
        System.out.println("Animal: " + bird.name + " of color " + bird.color);

        cat.voice();
        dog.voice();
        bird.move();

        System.out.println("==================");

        Animal zoo[] = {cat, dog, bird, parrot, snake};
        for (Animal animal : zoo) {
            // проверяем, что класс либо является Bird либо его наследник
            if (animal instanceof Bird) {
                ((Bird)animal).fly();
            }

            // проверяем, что класс является классом Parrot
            if (animal.getClass() == Parrot.class) {
                ((Parrot)animal).speak();
            } else {
                animal.voice();
            }
        }

    }
}
