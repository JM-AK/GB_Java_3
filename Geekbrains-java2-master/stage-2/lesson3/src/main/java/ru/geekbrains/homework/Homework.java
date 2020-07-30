package ru.geekbrains.homework;

import java.util.*;

public class Homework {

    /*
    Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
Найти список слов, из которых состоит текст (дубликаты не считать);
Посчитать сколько раз встречается каждое слово (использовать HashMap);

Написать простой класс PhoneBook(внутри использовать HashMap):
В качестве ключа использовать фамилию
В каждой записи всего два поля: phone, e-mail
Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов),
 и отдельный метод для поиска e-mail по фамилии. Следует учесть, что под одной фамилией
  может быть несколько записей. Итого должно получиться 3 класса Main, PhoneBook, Person.
     */

    public static void main(String[] args) {
        String[] words = new String[]{"A", "A", "B", "C", "D", "A"};
        System.out.println(getUniqueWords(words));
        printRepeatsCount(words);

        Person p1 = new Person("7906", "asdasd@asdasd.ru", "Ivanov");
        Person p2 = new Person("7907", "asdasd@asdasd.com", "Ivanov2");
        Person p3 = new Person("7908", "asdasd@asdasd.net", "Ivanov1");
        Person p4 = new Person("7909", "asdasd@asdasd.org", "Ivanov");

        Phonebook phonebook = new Phonebook();
        phonebook.add(p1, p2, p3, p4);
        System.out.println(phonebook.getPhones("Ivanov"));


    }

    public static ArrayList<String> getUniqueWords(String[] words) {
        HashSet<String> resultSet = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            resultSet.add(words[i]);
        }
        return new ArrayList<>(resultSet);
    }

    public static void printRepeatsCount(String[] words) {
        HashMap<String, Integer> result = new HashMap<>();
        for (String word : words) {

            result.put(word, result.getOrDefault(word, 0 ) + 1);

            /*Integer val = result.get(word);
            result.put(word, val == null ? 1 : val++);*/


            /*if (!result.containsKey(word)) {
                result.put(word, 1);
            } else {
                result.put(word, result.get(word) + 1);
            }*/
        }
        System.out.println(result);
    }
}
