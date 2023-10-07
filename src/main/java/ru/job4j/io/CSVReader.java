package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    private static List<String> list = new ArrayList<>();

    public static void handle(ArgsName argsName) throws IOException {
        validate(argsName);
        var delimiter = argsName.get("delimiter");
        var out = argsName.get("out");
        var filters = argsName.get("filter").split(",");
        var sl = System.lineSeparator();
        var sb = new StringBuilder();
        try (var scanner = new Scanner(Path.of(argsName.get("path")))) {
            if (scanner.hasNextLine()) {
                var header = List.of(scanner.nextLine().split(delimiter));
                if (!header.containsAll(List.of(filters))) {
                    throw new IllegalArgumentException("The specified filters are missing in the file");
                }
                sb.append(String.join(delimiter, filters)).append(sl);
                while (scanner.hasNextLine()) {
                    list.clear();
                    var line = scanner.nextLine().split(delimiter);
                    for (var filter : filters) {
                        var index = header.indexOf(filter);
                        list.add(line[index]);
                    }
                    sb.append(String.join(delimiter, list)).append(sl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("stdout".equals(out)) {
            System.out.println(sb);
        } else {
            Files.writeString(Path.of(out), sb.toString());
        }
    }

    private static void validate(ArgsName argsName) {
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException(String.format("Incorrect file name: %s", argsName.get("path")));
        }
        if (Character.isLetterOrDigit(argsName.get("delimiter").charAt(0))) {
            throw new IllegalArgumentException(String.format("Incorrect delimiter: %s", argsName.get("delimiter")));
        }
        if (!argsName.get("out").endsWith(".csv") && !("stdout".equals(argsName.get("out")))) {
            throw new IllegalArgumentException(String.format("Incorrect out %s", argsName.get("out")));
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("The program must have four parameters");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}