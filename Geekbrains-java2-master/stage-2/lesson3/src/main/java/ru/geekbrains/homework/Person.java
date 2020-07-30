package ru.geekbrains.homework;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private ArrayList<String> phone;
    private String email;
    private String surname;

    public Person(String phone, String email, String surname) {
        this.phone = new ArrayList<>();
        this.phone.add(phone);
        this.email = email;
        this.surname = surname;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(ArrayList<String> phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "phone=" + phone +
                ", email='" + email + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
