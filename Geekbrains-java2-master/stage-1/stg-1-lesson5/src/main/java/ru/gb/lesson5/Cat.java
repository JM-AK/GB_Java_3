package ru.gb.lesson5;

public class Cat {

    private static final int CURRENT_YEAR = 2020;
    private String name;
    private final int birthYear;
    private int age;
    private String color;
    private String gender;
    private static final int PAW_COUNT = 4;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return CURRENT_YEAR - this.birthYear;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Cat() {
        this.gender = "F";
        this.birthYear = CURRENT_YEAR;
    }


    public Cat(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public Cat(String name, int birthYear, String color) {
        this.name = name;
        this.birthYear = birthYear;
        this.color = color;
    }

    public void voice() {
        System.out.printf("Cat: %s meows", name);
        System.out.println();
    }

    public void jump() {
        System.out.printf("Cat: %s jumps", name);
        System.out.println();
    }

    @Override
    public boolean equals(Object anObject){
        if (anObject instanceof Cat) {
            Cat anotherCat = (Cat)anObject;
            return this.name.equals(anotherCat.name);
        }
        return false;
    }
}
