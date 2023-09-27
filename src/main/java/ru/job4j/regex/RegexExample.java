package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        /**
         * Создаем неизменяемый шаблон из переданной
         * последовательности символов.
         */
        Pattern pattern = Pattern.compile("Я учусь на Job4j");

        /**
         * Создаем сопоставитель шаблона и текста
         */
        String text1 = "Я учусь на Job4j";
        Matcher matcher1 = pattern.matcher(text1);
        String text2 = "Я учусь на курсе Job4j";
        Matcher matcher2 = pattern.matcher(text2);
        String text3 = "Я учусь на job4j";
        Matcher matcher3 = pattern.matcher(text3);
        /**
         * Метод matches() проверяет на полное соответствие
         * шаблона тексту
         */
        System.out.println(matcher1.matches());
        /**
         * System.out.println(matcher1.matches()) -> true
         */
        System.out.println(matcher2.matches());
        /**
         * System.out.println(matcher2.matches()) -> false
         */
        System.out.println(matcher3.matches());
        /**
         * System.out.println(matcher3.matches()) -> false
         * т.к. регулярные выражения регистрозависимые.
         */

        /**
         * Если регистр букв не принципиален, передаем в
         * метод compile() константу Pattern.CASE_INSENSITIVE
         */
        Pattern pattern1 = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);
        String text4 = "Job4j";
        Matcher matcher4 = pattern1.matcher(text4);
        String text5 = "job4j";
        Matcher matcher5 = pattern1.matcher(text5);
        System.out.println(matcher4.matches());
        /**
         * matcher4.matches() -> true;
         */
        System.out.println(matcher5.matches());
        /**
         * matcher5.matches() -> true;
         */

        /**
         * Метод find() проверяет шаблон на его присутствие в тексте
         */
        Matcher matcher6 = pattern1.matcher(text2);
        System.out.println(matcher6.find());
        /**
         * matcher6.find() -> true;
         */
        String text6 = "Job4j и Job4j и Job4j";
        Matcher matcher7 = pattern1.matcher(text6);
        while (matcher7.find()) {
            System.out.println("Найдено совпадение");
        }
        /**
         * 3 раза выведет "Найдено совпадение"
         */
        /**
         * Получить найденное совпадение с помощью
         * метода group(). Выводит ту часть строки
         * которая совпадает с шаблоном
         */
        while (matcher7.find()) {
            System.out.println("Найдено совпадение: " + matcher7.group());
        }
        /**
         * Найдено совпадение: Job4j
         * Найдено совпадение: Job4j
         * Найдено совпадение: Job4j
         */
        /**
         * Методы start()/end() получают индексы
         * в которых находятся первый/последний символ
         * искомой последовательности
         */
        Pattern pattern2 = Pattern.compile("Job4j");
        Matcher matcher8 = pattern2.matcher("Job4j1 и Job4j2 и Job4j3");
        while (matcher8.find()) {
            System.out.println("Найдено совпадение. iStart: " + matcher8.start()
                    + " iEnd: " + matcher8.end());
        }
        /**
         * Найдено совпадение. iStart: 0 iEnd: 5
         * Найдено совпадение. iStart: 9 iEnd: 14
         * Найдено совпадение. iStart: 18 iEnd: 23
         */
        /**
         * Найденные совпадения можно заменить
         * другой строкой с помощью replaceAll()
         */
        Pattern pattern3 = Pattern.compile("123");
        String text7 = "1231 и 1232 и 1233";
        Matcher matcher9 = pattern3.matcher(text7);
        String rsl = matcher9.replaceAll("Job4j");
        System.out.println(rsl);
        /**
         * Job4j1 и Job4j2 и Job4j3
         */
    }
}
