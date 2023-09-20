package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File f = file.toFile();
        FileProperty fp = new FileProperty(f.length(), f.getName());
        map.computeIfAbsent(fp, a -> new ArrayList<>());
        map.get(fp).add(file);
        return super.visitFile(file, attrs);
    }

    public void printDuplicates() {
        for (var fp : map.keySet()) {
            var list = map.get(fp);
            if (list.size() > 1) {
                System.out.printf("%s - %d" + System.lineSeparator(), fp.getName(), fp.getSize());
                for (var path : list) {
                    System.out.println(path);
                }
            }
        }
    }
}
