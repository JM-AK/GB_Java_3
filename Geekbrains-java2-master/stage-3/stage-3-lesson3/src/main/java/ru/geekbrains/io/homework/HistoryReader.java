package ru.geekbrains.io.homework;

import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;


public class HistoryReader {
    public void readFromLast(File file, int linesCount) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        int readLines = 0;
        long fileLength = file.length();
        try (RandomAccessFile raf = new RandomAccessFile(file, "r");){
            for (long pointer = fileLength; pointer >= 0; pointer--) {
                raf.seek(pointer);
                char c = (char) raf.read();
                if (c == '\n') {
                    lines.add(builder.reverse().toString());
                    builder = new StringBuilder();
                    readLines++;
                    if (readLines == linesCount) {
                        break;
                    }
                }
                builder.append(c);
            }
            Collections.reverse(lines);
        }
        // обработка массима строк
    }

    private String readHistory(String fileName) { //читаем файл с корнца, отбираем 100 строк
        StringBuilder history = new StringBuilder();
        File srcFile = new File(fileName);
        if (srcFile.exists()) {
            try (ReversedLinesFileReader file = new ReversedLinesFileReader(srcFile, Charset.defaultCharset())) {
                int counter = 0;
                String line = file.readLine();
                while (counter < 99 && line != null) {
                    history.insert(0, line + "\n");
                    line = file.readLine();
                    counter++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return history.toString();
    }

}
