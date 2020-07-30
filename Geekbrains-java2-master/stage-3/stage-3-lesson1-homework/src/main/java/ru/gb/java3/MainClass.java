package ru.gb.java3;

/*
* 1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
2. Написать метод, который преобразует массив в ArrayList;
3. Большая задача:
a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можете использовать ArrayList;
d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f,
* не важно в каких это единицах);
e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра,
* true - если их веса равны, false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку фруктов,
* нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается,
* а в другую перекидываются объекты, которые были в этой коробке;
g. Не забываем про метод добавления фрукта в коробку.
* */


import java.util.ArrayList;
import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) {
        /*Check task #1*/
        Integer[] sampleArr1 = new Integer[]{1,2,3,4,5,};
        sampleArr1 = getExchangeArray(sampleArr1, 0, 4);
        System.out.println(Arrays.deepToString(sampleArr1));

        /*Check task #2*/
        System.out.println(transformArrayToArrayList(sampleArr1));

        /*Check task #3*/

    }

    public static <T> T[] getExchangeArray (T[] array, int i, int j){
        if(array.length < 1) throw new RuntimeException("Error - array lenght is less then 2");
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        return array;
    }

    public static <T> ArrayList<T> transformArrayToArrayList (T[] array){
        ArrayList<T> list = new ArrayList<>();
        list.addAll(Arrays.asList(array));
        return list;
    }
}
