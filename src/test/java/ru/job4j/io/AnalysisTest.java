package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
    @Test
    void analysis(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("400 10:56:01");
            out.println("500 10:57:06");
            out.println("300 10:59:01");
        }
        File target = tempDir.resolve("target.csv").toFile();
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        String exp = "10:56:01;10:59:01;";
        assertThat(exp).isEqualTo(rsl.toString());
    }
}