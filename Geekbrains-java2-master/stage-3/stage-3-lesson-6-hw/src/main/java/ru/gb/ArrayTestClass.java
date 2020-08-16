package ru.gb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayTestClass {

    public static void main(String[] args) {

        System.out.println(checkContainNumsOneAndFour(new int[]{1,1,1,4,4}));

    }

    public int [] getArrAfterLastFour (int [] array) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0; i < array.length; i++){
            if(array[i] == 4) arrayList.clear();
            else arrayList.add(array[i]);
        }
        if (arrayList.size() == array.length) throw new RuntimeException();

        return arrayList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static boolean checkContainNumsOneAndFour(int[] array){
        int x1 = 0;
        int x4 = 0;

        for(int i = 0; i < array.length; i++){
            if(array[i] == 1) x1++;
            if(array[i] == 4) x4++;
        }
        return x1 > 0 && x4 > 0 && x1 + x4 == array.length;
    }

}
