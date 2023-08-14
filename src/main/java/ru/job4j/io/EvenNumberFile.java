package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                if (read % 2 == 0) {
                    text.append((char) read);
                }
            }
            System.out.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
