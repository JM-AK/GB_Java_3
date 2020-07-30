package ru.geekbrains.xo;

import ru.geekbrains.xo.enums.Error;

public class XoApplication {

    public static void main(String[] args) throws InterruptedException {
        new XoWindow();

        try {
            //...
        } catch (RuntimeException e) {
            System.out.println(Error.RUNTIME_EXCEPTION.getCode() + ": " + Error.RUNTIME_EXCEPTION.getMessage());
        }
    }
}
