package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

import static ru.job4j.cache.menu.Status.*;

public class Emulator {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var run = true;
        while (run) {
            System.out.println(MENU.getStatus());
            var userChoice = scanner.nextLine();
            System.out.println();
            if (userChoice.equals(LOAD_CACHE.getStatus())) {
                var dirFileCache = getDir(scanner);
                var nameFile = getFile(scanner);
                dirFileCache.get(nameFile);
            } else if (userChoice.equals(GET_CACHE.getStatus())) {
                var dirFileCache = getDir(scanner);
                var nameFile = getFile(scanner);
                System.out.println("Содержимое файла " + nameFile + ":");
                System.out.println(dirFileCache.get(nameFile));
                System.out.println();
            } else {
                run = false;
                System.out.println(EXIT.getStatus());
            }
        }
    }

    private static DirFileCache getDir(Scanner scanner) {
        System.out.print(CACHE_DIR.getStatus());
        return new DirFileCache(scanner.nextLine());
    }

    private static String getFile(Scanner scanner) {
        System.out.print(NAME_FILE.getStatus());
        return scanner.nextLine();
    }
}