package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    void when3ThenFizz() {
        int number = 3;
        String str = Fool.game(number);
        assertThat(str).isEqualTo("Fizz");
    }

    @Test
    void when5ThenBuzz() {
        int number = 5;
        String str = Fool.game(number);
        assertThat(str).isEqualTo("Buzz");
    }

    @Test
    void when15ThenFizzBuzz() {
        int number = 15;
        String str = Fool.game(number);
        assertThat(str).isEqualTo("FizzBuzz");
    }

    @Test
    void when22Then22() {
        int number = 22;
        String str = Fool.game(number);
        assertThat(str).isEqualTo(String.valueOf(number));
    }
}