package ru.geekbrains.homework;

import java.util.Arrays;

public class Main {
    /*
    сть строка вида: "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0"; (другими словами матрица 4x4)
        10 3 1 2
        2 3 2 2
        5 6 7 1
        300 3 1 0
        Написать метод, на вход которого подаётся такая строка, метод должен преобразовать строку в двумерный массив типа String[][];

        2. Преобразовать все элементы массива в числа типа int, просуммировать, поделить полученную сумму на 2, и вернуть результат;
        3. Ваши методы должны бросить исключения в случаях:
        Если размер матрицы, полученной из строки, не равен 4x4;
        Если в одной из ячеек полученной матрицы не число; (например символ или слово)
        4. В методе main необходимо вызвать полученные методы, обработать возможные исключения и вывести результат расчета.
        5. * Написать собственные классы исключений для каждого из случаев
     */

    private static class IncorrectMatrixSize extends RuntimeException {

        public IncorrectMatrixSize(String message) {
            super(message);
        }
    }

    private static final int MATRIX_SIZE = 4;

    public static void main(String[] args) {
        String test1 = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
        String test2 = "10 3 1 2\n2 3 2\n5 6 7 1\n300 3 1 0";
        String test3 = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 D 0";

        try {
            double result = sumAndDiv(stringToMatrix(test1));
            System.out.println("result1 = " + result);

            result = sumAndDiv(stringToMatrix(test2));
            System.out.println("result2 = " + result);

            result = sumAndDiv(stringToMatrix(test3));
            System.out.println("result3 = " + result);
        } catch (IncorrectMatrixSize | NumberFormatException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static String[][] stringToMatrix(String str) throws IncorrectMatrixSize {
        String[] arr1 = str.split("\n");

        if (arr1.length != MATRIX_SIZE) {
            throw new IncorrectMatrixSize(
                    String.format("Кол-во элементов массива не соответствует требуемому: строка '%s', разделитель \\n", str));
        }

        String[][] result = new String[MATRIX_SIZE][];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i].split(" ");
            if (result[i].length != MATRIX_SIZE) {
                throw new IncorrectMatrixSize(
                        String.format("Кол-во элементов массива не соответствует требуемому: строка '%s', разделитель \" \"", arr1[i]));
            }
        }
        System.out.println(Arrays.deepToString(result));
        return result;
    }

    public static double sumAndDiv(String[][] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    result += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    System.out.println("sumAndDiv : " + e.getMessage());
                    throw e;
                }
            }
        }
        return result / 2d;
    }
}
