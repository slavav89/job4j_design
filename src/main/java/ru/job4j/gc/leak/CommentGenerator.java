package ru.job4j.gc.leak;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {

    public static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";
    private List<Comment> comments = new ArrayList<>();
    private List<String> phrases;
    private UserGenerator userGenerator;
    private Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        phrases = read(PATH_PHRASES);
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        var separator = System.lineSeparator();
        var str = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            String comment = str
                    .append(phrases.get(random.nextInt(phrases.size())))
                    .append(separator)
                    .append(phrases.get(random.nextInt(phrases.size())))
                    .append(separator)
                    .append(phrases.get(random.nextInt(phrases.size())))
                    .toString();
            comments.add(new Comment(comment,
                    userGenerator.randomUser()));
            str.delete(0, str.length());
        }
    }
}