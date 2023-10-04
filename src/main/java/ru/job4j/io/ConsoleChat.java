package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private Random random = new Random();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Метод run() содержит логику консольного чата
     */
    public void run() {
        List<String> dialogue = new ArrayList<>();
        List<String> phrases = readPhrases();
        Scanner input = new Scanner(System.in);
        boolean answer = true;
        String in = input.nextLine();
        while (!Objects.equals(in, OUT)) {
            dialogue.add(in);
            if (Objects.equals(in, STOP)) {
                answer = false;
            } else if (Objects.equals(in, CONTINUE)) {
                answer = true;
            }
            if (answer) {
                int index = random.nextInt(phrases.size());
                String reply = phrases.get(index);
                System.out.println(reply);
                dialogue.add(reply);
            }
            in = input.nextLine();
        }
        dialogue.add(in);
        saveLog(dialogue);
    }

    /**
     * метод readPhrases() читает фразы из файла "data/answers.txt"
     */
    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/answers.txt"))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * метод saveLog() сохраняет лог чата в файл "data/dialog.txt"
     */
    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter("data/dialog.txt", StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/dialog.txt", "data/answers.txt");
        cc.run();
    }
}
