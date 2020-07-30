package ru.geekbrains.homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class FileWorks {
    private static final Random rnd = new Random();
    private static final int CHAR_BOUND_L = 65;
    private static final int CHAR_BOUND_H = 90;
    private static final int FILES_AMOUNT = 10;
    private static final int WORDS_AMOUNT = 5;
    private static final int WORD_LENGTH = 10;
    private static final String TO_GOOGLE = "geekbrains";

    private static String generateSymbols(int amount) {
        StringBuilder sequence = new StringBuilder();
        for (int i = 0; i < amount; i++)
            sequence.append((char) (CHAR_BOUND_L + rnd.nextInt(CHAR_BOUND_H - CHAR_BOUND_L)));
        return sequence.toString();
    }

    private static void writeFileContents(String fileName, int length) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(generateSymbols(length).getBytes());
        fos.flush();
        fos.close();
    }

    private static void writeFileContents(String fileName, int words, int length) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        for (int i = 0; i < words; i++) {
            if(rnd.nextInt(WORDS_AMOUNT) == WORDS_AMOUNT / 2)
                fos.write(TO_GOOGLE.getBytes());
            else
                fos.write(generateSymbols(length).getBytes());
            fos.write(' ');
        }
        fos.flush();
        fos.close();
    }

    private static void concatenate(String file_in1, String file_in2, String file_out) throws IOException {
        FileOutputStream fos = new FileOutputStream(file_out);
        int ch;
        FileInputStream fis = new FileInputStream(file_in1);
        while ((ch = fis.read()) != -1)
            fos.write(ch);
        fis.close();

        fis = new FileInputStream(file_in2);
        while ((ch = fis.read()) != -1)
            fos.write(ch);
        fis.close();

        fos.flush();
        fos.close();
    }

    private static boolean isInFile(String fileName, String search) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        byte[] searchSequence = search.getBytes();
        int ch;
        int i = 0;              // geekgeekbrains
        while ((ch = fis.read()) != -1) {
            if (ch == searchSequence[i])
                i++;
            else {
                i = 0;
                if (ch == searchSequence[i]) i++;
            }
            if (i == searchSequence.length) {
                fis.close();
                return true;
            }
        }
        fis.close();
        return false;
    }

    private static String[] searchMatch(String[] files, String search) throws IOException {
        String[] list = new String[files.length];
        int count = 0;
        File path = new File( new File(".").getCanonicalPath() );
        File[] dir = path.listFiles();
        for (int i = 0; i < dir.length; i++) {
            if (dir[i].isDirectory()) continue;
            for (int j = 0; j < files.length; j++)
                if (dir[i].getName().equals(files[j]))
                    if (isInFile(dir[i].getName(), search)) {
                        list[count] = dir[i].getName();
                        count++;
                        break;
                    }
        }
        return list;
    }

    public static void main(String[] args) {
        String[] fileNames = new String[FILES_AMOUNT];
        for (int i = 0; i < fileNames.length; i++)
            fileNames[i] = "file_" + i + ".txt";

        // Here we can use Scanner to ask user for a word.
        // I decided to use it as a constant, to show
        // the flexibility of a searching method.
        //
        // Scanner sc = new Scanner(System.in);
        // String TO_GOOGLE = sc.next();
        // sc.close();

        try {
            //#1
            for (int i = 0; i < fileNames.length; i++)
                if (i < 2)
                    writeFileContents(fileNames[i], 100);
                else
                    writeFileContents(fileNames[i], WORDS_AMOUNT, WORD_LENGTH);
            System.out.println("First task results are in file_0 and file_1.");
            //#2
            concatenate(fileNames[0], fileNames[1], "FILE_OUT.txt");
            System.out.println("Second task result is in FILE_OUT.");
            //#3
            System.out.println("Check result in file_2 for third task is: " + isInFile(fileNames[2], TO_GOOGLE));
            //#4
            System.out.println("And here's the result of the fourth task. Go ahead and check it!");
            String[] result = searchMatch(fileNames, TO_GOOGLE);
            for (int i = 0; i < result.length; i++)
                if (result[i] != null)
                    System.out.println("File '" + result[i] + "' contains the searched word '" + TO_GOOGLE + "'");
        }
        catch (Exception ex) { throw new RuntimeException(ex); }
    }
}
