package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info result;
        if (previous.equals(current)) {
            result = new Info(0, 0, 0);
        } else {
            int added = 0, changed = 0;
            Map<Integer, String> map = new HashMap<>();
            for (var element : previous) {
                map.put(element.getId(), element.getName());
            }
            for (var element : current) {
                String name = map.put(element.getId(), element.getName());
                if (name == null) {
                    added++;
                } else if (!name.equals(element.getName())) {
                    changed++;
                }
            }
            int deleted = previous.size() - (current.size() - added);
            result = new Info(added, changed, deleted);
        }
        return result;
    }
}