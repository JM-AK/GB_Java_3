package ru.geekbrains;

import java.io.*;
import java.util.Scanner;

public class MainClass {


    public static int div (int x, int y) throws ArithmeticException {
        return x / y;
    }

    public static void main(String[] args) {

        InputStream is;
        OutputStream os;



        byte[] chars = {'h', 'e', 'l', 'l', 'o'};
        String s = "hello1";
        try {
            PrintStream ps = new PrintStream(new FileOutputStream("test.txt", true));
            ps.println(s);
            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Scanner scanner = new Scanner(new FileInputStream("test.txt"));
            System.out.println(scanner.next());
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            System.out.println();
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            System.out.println(1);
        }
    }

    private static void stringBuilderExample() {
        String s0 = "Example";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
           s0 += i;
        }
        System.out.println(System.currentTimeMillis() - start);

        StringBuilder s1 = new StringBuilder("Example");
        start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            s1.append(i);
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(s0.equals(s1.toString()));
    }

    private static void stringExample() {
        Scanner scanner = new Scanner(System.in);
        String s0 = "hello"; // new String ("hello world");
        String s1 = "hello";

        String s2 = "hel";
        String s3 = "lo";
        String s4 = s2 + s3; // new String (s2 + s3)
        String s5 = scanner.next();


        System.out.println(s0.equals(s1));
        System.out.println(s0 == s4);
        System.out.println(s0 == s5);

        System.out.println(s0.equals(s1));
        System.out.println(s0.equals(s4));
        System.out.println(s0.equals(s5));
    }
}
