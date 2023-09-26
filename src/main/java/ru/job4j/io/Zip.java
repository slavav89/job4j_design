package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream((new BufferedOutputStream(new FileOutputStream(target))))) {
            for (var path : sources) {
                zip.putNextEntry(new ZipEntry((path.toString())));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName args) {
        if (!Files.isDirectory(Path.of(args.get("d")))) {
            throw new IllegalArgumentException("Use the correct directory");
        }
        if (!args.get("e").startsWith(".")) {
            throw new IllegalArgumentException("The extension should start with \".\"");
        }
        if (!args.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("use the correct archive type");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Use three arguments");
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        Path source = Path.of(argsName.get("d"));
        List<Path> list = Search.search(source, path -> !path.toString().endsWith(argsName.get("e")));
        zip.packFiles(list, new File(argsName.get("o")));
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}