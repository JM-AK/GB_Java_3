package ru.geekbrains;

import java.util.*;

public class Application {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Jan", 1);
        map.put("Feb", 2);
        map.put("Mar", 3);
        map.put("Apr", 4);
        map.put("Jan", 5);


        for (String key : map.keySet()) {
//            System.out.println(key + " " + map.get(key));
        }


//        System.out.println(map);
        setExample();
    }

    private static void setExample() {
        Box box1 = new Box(1, 1);
        Box box2 = new Box(2, 2);
        Box box3 = new Box(3, 3);
        Box box4 = new Box(4, 4);
        Box box5 = new Box(1, 1);


        TreeSet<Box> set = new TreeSet<>();
        set.add(box1);
        set.add(box2);
        set.add(box3);
        set.add(box4);

        set.add(box5);
        System.out.println(set);

        HashSet<String> setStr = new HashSet<>();
        setStr.add("Jan");
        setStr.add("Feb");
        setStr.add("Mar");
        setStr.add("Apr");
        setStr.add("Jan");
        System.out.println(setStr);

    }

    private static void arrListExample() {
        LinkedList<Integer> arrInt = new LinkedList<>();
        arrInt.add(10);
        arrInt.add(20);
        arrInt.add(30);

        for (Integer i : arrInt) {
            System.out.println(i);
        }

        System.out.println(arrInt);
        System.out.println("==========");

        for (int i = 0; i < arrInt.size() ; i++) {
            System.out.println(arrInt.get(i));
        }
        System.out.println("==========");

        Iterator<Integer> iterator = arrInt.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            System.out.println(i);
        }
    }

    private static void intExample() {
        int i = 10;
        Integer integetClass = 10;
        Integer[] arr = {10, 11, 20};

        for (int j = 0; j < arr.length ; j++) {
            Integer in = arr[i];
            //....
        }
    }

}
