package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            boolean flag = false;
            String[] strings = in.lines().toArray(String[]::new);
            for (var string : strings) {
                String[] array = string.split(" ");
                if (flag != (Integer.parseInt(array[0]) > 300)) {
                    flag = !flag;
                    out.append(array[1]).append(";").append(flag ? "" : System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}

