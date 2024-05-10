package ru.job4j.ood.srp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * метод addPerson(String name) в классе People
 * добавляет в список новое имя, а также создает при добавлении
 * новый файл с обновленным списком имен, тем самым нарушая принцип SRP
 * добавляя в список и создавая и записывая в файл в одном классе.
 */
public class People {
    private final List<String> people = new ArrayList<>();

    public boolean addPerson(String name) throws IOException {
        var result = false;
        if (!people.contains(name)) {
            people.add(name);
            result = true;
        }
        if (result) {
            Path path = Files.createFile(Path.of(name));
            try (BufferedWriter w = Files.newBufferedWriter(path)) {
                for (String person : people) {
                    w.write(person);
                }
            }
        }
        return result;
    }

    public List<String> getPeople() {
        return people;
    }
}
