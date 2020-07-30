package ru.geekbrains.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Phonebook {

    private HashMap<String, HashSet<Person>> phoneBook = new HashMap<>();

    public void add(Person... personArr) {
        for (Person person : personArr) {
            add(person);
        }
    }

    public void add(Person person) {
        String surname = person.getSurname().toLowerCase().trim();

        HashSet<Person> persons = phoneBook.get(surname);
        if (persons == null) {
            persons = new HashSet<>();
            phoneBook.put(surname, persons);
        }
        persons.add(person);
    }

    public ArrayList<String> getPhones(String surname) {
        String surnameToSearch = surname.toLowerCase().trim();
        HashSet<Person> persons = phoneBook.get(surnameToSearch);
        ArrayList<String> phones = new ArrayList<>();
        for (Person person : persons) {
            phones.addAll(person.getPhone());
        }
        return phones;
    }

    public List<String> getEmails(String surname) {
        String surnameToSearch = surname.toLowerCase().trim();
        HashSet<Person> persons = phoneBook.get(surnameToSearch);
        ArrayList<String> phones = new ArrayList<>();

        return persons
                .stream()
                //.map(person -> person.getEmail())
                .map(Person::getEmail)
                .collect(Collectors.toList());
    }


}
