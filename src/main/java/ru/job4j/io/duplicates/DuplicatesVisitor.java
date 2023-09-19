package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> map = new HashMap<>();
    private final HashSet<FileProperty> set = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            File f = file.toFile();
            FileProperty fp = new FileProperty(f.length(), f.getName());
            List<Path> list;
            if (map.containsKey(fp)) {
                set.add(fp);
                list = map.get(fp);
                list.add(file.toAbsolutePath());
                map.replace(fp, list);
            } else {
                list = new ArrayList<>();
                list.add(file.toAbsolutePath());
                map.put(fp, list);
            }
        }
        return super.visitFile(file, attrs);
    }

    public void printDuplicates() {
        for (var fp : set) {
            System.out.printf("%s - %d" + System.lineSeparator(), fp.getName(), fp.getSize());
            for (var path : map.get(fp)) {
                System.out.println(path);
            }
        }
    }
}
