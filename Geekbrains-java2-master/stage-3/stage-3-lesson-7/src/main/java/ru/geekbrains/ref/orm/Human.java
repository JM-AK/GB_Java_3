package ru.geekbrains.ref.orm;

@TableXT(table = "humans")
public class Human {

    @FieldXT
    private int id;

    @FieldXT
    private String name;

    @FieldXT
    private String surname;

    @FieldXT
    private int age;

    @FieldXT
    private int salary;

    public Human(int id, String name, String surname, int age, int salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
    }
}
