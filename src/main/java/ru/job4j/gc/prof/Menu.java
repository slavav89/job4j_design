package ru.job4j.gc.prof;

import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

import static ru.job4j.gc.prof.TypeSort.*;
import static ru.job4j.gc.prof.Status.*;

public class Menu {
    public static void main(String[] args) {
        Data store = new RandomArray(new Random());
        Sort merge = new MergeSort();
        Sort bubble = new BubbleSort();
        Sort insert = new InsertSort();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println(MENU.getStatus());
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (CREATE_ARRAY.getNumber() == userChoice) {
                System.out.println(SELECT.getStatus());
                int size = Integer.parseInt(scanner.nextLine());
                store.insert(size);
                System.out.println(ARRAY_INFO.getStatus() + size);
            } else if (MERGE_SORT.getNumber() == userChoice) {
                System.out.printf("%s%s%s%n", merge.getClass().getSimpleName(), BEGIN.getStatus(), LocalTime.now());
                merge.sort(store);
                System.out.printf("%s%s%s%n", merge.getClass().getSimpleName(), END.getStatus(), LocalTime.now());
            } else if (BUBBLE_SORT.getNumber() == userChoice) {
                System.out.printf("%s%s%s%n", bubble.getClass().getSimpleName(), BEGIN.getStatus(), LocalTime.now());
                bubble.sort(store);
                System.out.printf("%s%s%s%n", bubble.getClass().getSimpleName(), END.getStatus(), LocalTime.now());
            } else if (INSERT_SORT.getNumber() == userChoice) {
                System.out.printf("%s%s%s%n", insert.getClass().getSimpleName(), BEGIN.getStatus(), LocalTime.now());
                insert.sort(store);
                System.out.printf("%s%s%s%n", insert.getClass().getSimpleName(), END.getStatus(), LocalTime.now());
            } else {
                run = false;
                System.out.println("Конец работы");
            }
        }
    }
}