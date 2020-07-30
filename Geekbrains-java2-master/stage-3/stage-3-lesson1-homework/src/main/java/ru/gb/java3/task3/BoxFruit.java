package ru.gb.java3.task3;

/*
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

public class BoxFruit <F extends Fruit> {
    private ArrayList<F> boxList = new ArrayList<>();

    public BoxFruit (F ... fruits){
        this.boxList.addAll(Arrays.asList(fruits));
    }

    /*сделал счет таким образом, суммирует индивидуально т.к. если создать коробку с типом Fruit, то не будет универслаьного веса
    * не знаю как ограничить только двумя класами наследников */
    public double getWeight(){
        if (boxList.isEmpty()) return 0;
        double w = 0;
        for (int i = 0; i < boxList.size(); i++) {
            w+=boxList.get(i).getWeight();
        }
        return w;
    }

    public boolean compare(BoxFruit<?> another) {
        return this.getWeight() == another.getWeight();
    }

    public void moveFruitsToAnotherBox (BoxFruit<F> another){
        another.boxList.addAll(this.boxList);
        this.boxList.clear();
    }

    public void addFruits(F...fruits){
        this.boxList.addAll(Arrays.asList(fruits));
    }

}
