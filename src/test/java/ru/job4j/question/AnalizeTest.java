package ru.job4j.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class AnalizeTest {

    User u1, u2, u3;
    Set<User> previous, current;

    @BeforeEach
    void initData() {
        u1 = new User(1, "A");
        u2 = new User(2, "B");
        u3 = new User(3, "C");
        previous = Set.of(u1, u2, u3);
    }

    @Test
    void whenNotChanged() {
        current = Set.of(u1, u2, u3);
        assertThat(Analize.diff(previous, current))
                .isEqualTo(new Info(0, 0, 0));
    }

    @Test
    void whenOneChanged() {
        current = Set.of(u1, new User(2, "BB"), u3);
        assertThat(Analize.diff(previous, current))
                .isEqualTo(new Info(0, 1, 0));
    }

    @Test
    void whenOneDeleted() {
        current = Set.of(u1, u3);
        assertThat(Analize.diff(previous, current))
                .isEqualTo(new Info(0, 0, 1));
    }

    @Test
    void whenOneAdded() {
        current = Set.of(u1, u2, u3, new User(4, "D"));
        assertThat(Analize.diff(previous, current))
                .isEqualTo(new Info(1, 0, 0));
    }

    @Test
    void whenAllChanged() {
        current = Set.of(new User(1, "AA"), u2, new User(4, "D"));
        assertThat(Analize.diff(previous, current))
                .isEqualTo(new Info(1, 1, 1));
    }

    @Test
    void whenTwoAddedThreeChangedTwoDeleted() {
        User u4 = new User(4, "D");
        User u5 = new User(5, "F");
        User u6 = new User(6, "G");
        previous = Set.of(u1, u2, u3, u4, u5, u6);
        current = Set.of(u4, new User(7, "H"), new User(8, "I")
                , new User(1, "AA"), new User(2, "BB"), new User(3, "CC"));
        assertThat(Analize.diff(previous, current))
                .isEqualTo(new Info(2, 3, 2));
    }
}