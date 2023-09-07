package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String[] array = read.lines().toArray(String[]::new);
            for (var str : array) {
                if (!str.isEmpty() && !str.startsWith("#")) {
                    var arrayString = str.split("=", 2);
                    if (arrayString[0].isEmpty()
                            || arrayString[arrayString.length - 1].isEmpty()
                            || arrayString.length < 2) {
                        throw new IllegalArgumentException();
                    }
                    values.put(arrayString[0], arrayString[1]);
                }
            }
            values.put(array[0], array[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}