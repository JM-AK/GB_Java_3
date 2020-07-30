package ru.geekbrains.exception;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class MainExceptionClass {

    public static int div (int x, int y) {
        if (y == 0) {
            throw new RuntimeException();
        }
        return x / y;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        try (IOStream ioStream = new IOStream()) {
            ioStream.open();
            ioStream.write(); // DB not found
        }

        System.out.println("Continue...");
    }


    private static class IOStream implements Closeable {
        public void open() throws FileNotFoundException {
//            throw new FileNotFoundException("File not found");
            System.out.println("open");
        }

        public void write() throws IOException {
            throw new IOException("DB not found");
//            System.out.println("write");
        }

        @Override
        public void close() throws FileNotFoundException {
            throw new FileNotFoundException("vse propalo..");
        }

    }
}
