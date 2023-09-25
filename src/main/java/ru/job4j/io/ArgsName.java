package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '%s' is missing".formatted(key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (var string : args) {
            if (!string.contains("=")) {
                throw new IllegalArgumentException("Error: This argument '%s' does not contain an equal sign"
                        .formatted(string));
            }
            if (string.startsWith("-=")) {
                throw new IllegalArgumentException("Error: This argument '%s' does not contain a key"
                        .formatted(string));
            }
            if (!string.startsWith("-")) {
                throw new IllegalArgumentException("Error: This argument "
                        + "'%s' does not start with a '-' character".formatted(string));
            }
            String[] keyValue = string.split("=", 2);
            var key = keyValue[0].substring(1);
            var value = keyValue[1];
            if (value.isBlank()) {
                throw new IllegalArgumentException("Error: This argument '%s' does not contain a value"
                        .formatted(string));
            }
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
