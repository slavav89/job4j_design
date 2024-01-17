package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.*;

public class ImportDB {

    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines()
                    .map(s -> s.split(";", 2))
                    .peek(this::checkArray)
                    .forEach(k -> users.add(new User(k[0], k[1])));
        }
        return users;
    }

    private void checkArray(String[] array) {
        if (array.length != 2) {
            throw new IllegalArgumentException("Размер массива не равен 2");
        }
        if (array[0].isEmpty()) {
            throw new IllegalArgumentException("Первый элемент массива пустой");
        }
        if (array[1].isEmpty()) {
            throw new IllegalArgumentException("Второй элемент массива пустой");
        }
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("driver_class"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("username"),
                config.getProperty("password")
        )) {
            try (var statement = connection.createStatement()) {
                String sql = String.format(
                        "CREATE TABLE IF NOT EXISTS users(%s, %s);",
                        "name TEXT",
                        "email TEXT"
                );
                statement.execute(sql);
            }
            for (User user : users) {
                try (PreparedStatement preparedStatement
                             = connection.prepareStatement("INSERT INTO users(name, email) VALUES(?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("spammer.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "data/dump.txt");
        dataBase.save(dataBase.load());
    }
}