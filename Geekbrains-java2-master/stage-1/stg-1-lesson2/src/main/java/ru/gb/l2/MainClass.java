package ru.gb.l2;

import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) {
//        switchDemo("Alex");

//        cycleDemo();
//        cycleWhileDemo();
//        cycleForEachDemo();
//        printPifagorTable(10);

       int[] arr = new int[10];
       arr[0] = 5;
       arr[1] = 10;
       arr[9] = 11;

       for (int i = 0; i < arr.length; i++) {
//           arr[i] = 10;
       }
       System.out.println(Arrays.toString(arr));

       int[] arrPreDef = {1, 2, 3, 4};

       System.out.println(Arrays.toString(arrPreDef));

       int[] arri = new int[10];

       int[] arrj = new int[arri.length * 2];

       System.out.println(Arrays.toString(arri));

       int[][] matrix = new int[10][];

       int[][] twoDimArr = new int[10][10];
//       twoDimArr[0] = new int[5];
//       twoDimArr[0][4] = 10;
//       twoDimArr[1] = new int[50];

       for (int i = 0; i < twoDimArr.length; i ++) {
           for (int j = 0; j < twoDimArr[i].length; j++) {
               twoDimArr[i][j] = 10;
           }

       }

        int[][] matrix2 = new int[10][10];





       int[][] twoDimPredefArr =
               {
                   {1, 2, 3},
                   {4, 5, 6}
               };


        System.out.println(Arrays.deepToString(twoDimArr));



    }



    private static void continueCycle() {
        for (int i = 1; i < 10; i++) {
            System.out.print("in = " + i + "; ");
            if (i % 2 == 0) {
                System.out.println("continue; ");
                continue;
            }
            // .. сложная логика на 30 строк
            System.out.println("out = " + i);
        }
    }


    private static void printPifagorTable(int x) {
        for (int i = 1; i < x + 1; i++) {
            for (int j = 1; j < x + 1; j ++) {
                System.out.printf("%4d", i * j);
            }
            System.out.println();
        }
    }


    private static void cycleForEachDemo() {
        String[] strArr = {"Java", "Language", "GB", "cycle"};

        for (String s : strArr) {
            int i = 10;
            System.out.println(s);
        }
    }



    private static void cycleWhileDemo() {
        int i = 2;

        while (i < 2) {
            System.out.print("while: ");
            System.out.print(i + " ");
            i++;
        }
        System.out.println();

        i = 2;
        do {
            System.out.print("dowhile: ");
            System.out.print(i + " ");
            i++;
        } while (i < 2);
    }

    private static void cycleDemo() {
        // Инициализация - начальное значение
        // Условие окончания
        // Тело цикла

        // while {}, for, foreach, do {} while

        int res = 0;
        System.out.print("while: ");

        // Используется тогда, когда мы четко не можем сказать за сколько шагов закончится, но значев условие окончания
        while (res < 2) {
            System.out.print(res + " ");
            res++;
        }
        System.out.println();


        boolean result = false;
        while (result != true) {
        //    result = checkComplexLogic(...);
        }


        System.out.print("for: ");
        // Используется тогда, когда мы четко знаем за сколько шагов должен закончится
        for (int j = 2; j < 10; j += 2) {
            System.out.print(j + " ");
        }

        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {

            }
        }
    }

    private static void switchDemo(String name) {

        /* if (name == "Igor") {
            System.out.println("Give package to table 1");
        } else if (name == "Alex") {
            System.out.println("Give package to table 3");
        } else if (name == "Vasya") {
            System.out.println("Give package to table 5");
        } else {
            System.out.println("no one");
        } */


        switch(name) {
            case "Igor" :
                System.out.println("Give package to table 1");
                break;
            case "Alex"  :
            case "Alex1" :
            case "Alex2" :
            case "Alex3" :
            case "Vasya" :
                System.out.println("Give package to table 5");
                break;
            default:
                System.out.println("no one");
        }
    }

}
