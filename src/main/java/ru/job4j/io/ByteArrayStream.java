package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayStream {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{'j', 'a', 'v', 'a'};
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        int data;
        while ((data = stream.read()) != -1) {
            System.out.print((char) data);
        }
        System.out.println();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] byte2 = "java".getBytes();
        out.writeBytes(bytes);
        System.out.println(out);
        byte[] byte3 = out.toByteArray();
        try (FileOutputStream file = new FileOutputStream("data/message.txt")) {
            out.writeTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
