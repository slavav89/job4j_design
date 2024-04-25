package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static String game(int startAt) {
        String str;
        if (startAt % 3 == 0 && startAt % 5 == 0) {
            str = "FizzBuzz";
        } else if (startAt % 3 == 0) {
            str = "Fizz";
        } else if (startAt % 5 == 0) {
            str = "Buzz";
        } else {
            str = String.valueOf(startAt);
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        while (startAt < 100) {
            String str = game(startAt);
            if (startAt % 2 == 1) {
                System.out.println(str);
            } else {
                var input = new Scanner(System.in);
                var answer = input.nextLine();
                if (!str.equals(answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
            }
            startAt++;
        }
    }
}
