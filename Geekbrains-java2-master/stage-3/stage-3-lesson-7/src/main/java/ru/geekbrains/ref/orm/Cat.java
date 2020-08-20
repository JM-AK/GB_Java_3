package ru.geekbrains.ref.orm;

@TableXT(table = "cats")
public class Cat {
    @FieldXT
    private int id;

    @FieldXT
    private String name;

    @FieldXT
    private int age;

    private String nick;

    public Cat(int id, String name, int age, String nick) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nick = nick;
    }
}
