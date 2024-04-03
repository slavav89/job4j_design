package ru.job4j.gc.leak;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    public static final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    public static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    public static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    public List<String> names;
    public List<String> surnames;
    public List<String> patrons;
    private List<User> users = new ArrayList<>();
    private Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        var str = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            users.add(new User(str
                    .append(surnames.get(random.nextInt(surnames.size())))
                    .append(" ")
                    .append(names.get(random.nextInt(names.size())))
                    .append(" ")
                    .append(patrons.get(random.nextInt(patrons.size()))).toString()));
            str.delete(0, str.length());
        }
    }

    private void readAll() {
        names = read(PATH_NAMES);
        surnames = read(PATH_SURNAMES);
        patrons = read(PATH_PATRONS);
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

    public List<User> getUsers() {
        return users;
    }
}