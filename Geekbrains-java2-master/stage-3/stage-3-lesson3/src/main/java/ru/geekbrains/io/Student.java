package ru.geekbrains.io;

import java.io.Serializable;

public class Student extends Human implements Serializable {

    private static final long serialVersionUID = 5456625501757712597L;

    int id;
    String name;
    Book book;

    public Student(int id, String name) {
        super("asdasd");
        System.out.println("const-with-params");
        this.id = id;
        this.name = name;
    }

    public void info() {
        System.out.println(this.toString());
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", book='" + book.name + '\'' +
                '}';
    }
}
