package ru.geekbrains.io;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Book book = new Book();
        book.setName("Jungle book part I");
        Student student1 = new Student(1, "Alex");
        student1.setBook(book);

        Student student2 = new Student(2, "Alex");
        student2.setBook(book);

        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("./dir/stud.ser"));
        oos1.writeObject(student1);

        book.setName("Jungle book part II");
        oos1.reset();
        oos1.writeObject(student2);

        ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream("./dir/stud.ser"));
        Student studentI1 = (Student)ois1.readObject();
        Student studentI2  = (Student)ois1.readObject();
        studentI1.info();
        studentI2.info();
        System.out.println(studentI1.book);
        System.out.println(studentI2.book);

    }

    private static void bufferExample() {
        File file = new File("./dir/1.txt");

        try (BufferedOutputStream bof = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < 20; i++) {
                bof.write(65);
            }
            /// some code ...
            bof.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void russianRead() {
        File file = new File("./dir/1.txt");
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            int x;
            while ((x = isr.read()) != -1) {
                System.out.print((char)x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileRWExample(File file) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte b = 65;
            byte[] arrSymbols = new byte[8192];
            Arrays.fill(arrSymbols, b);
            for (int i = 0; i < 2_000_000 / 8192; i++) {
                fos.write(arrSymbols);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            int x;
            while ((x = fis.read()) != -1) {
                System.out.println(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileMethods() {
        File file = new File("./dir/1.txt");
        System.out.println(file.length());
        System.out.println(file.isAbsolute());
        System.out.println(file.isDirectory());

        System.out.println(file.mkdirs());
        System.out.println(Arrays.toString(file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("1");
            }
        })));


        System.out.println(Arrays.toString(file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.length() > 100;
            }
        })));
    }

}
