package ru.job4j.template;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorStringTest {
    private GeneratorString gs;

    @BeforeEach
    public void gs() {
        gs = new GeneratorString();
    }

    @Test
    void whenNotKeyInMapThenException() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of("name", "Petr Arsentev", "sub", "you");
        assertThatThrownBy(() -> gs.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMapHasExtraKeysThenException() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args =
                Map.of("name", "Petr Arsentev", "subject", "you", "sub", "you");
        assertThatThrownBy(() -> gs.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenKeyIsNameAndValueIsNullThenException() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of("name", null, "subject", "you");
        assertThatThrownBy(() -> gs.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenTemplateDoesNotHaveKeysThenException() {
        String template = "I am a name, Who are subject? ";
        Map<String, String> args = Map.of("name", "Petr Arsentev", "subject", "you");
        assertThatThrownBy(() -> gs.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenTemplateHas3KeysThenException() {
        String template = "I am a ${name}, Who are ${subject}? How old are ${subject2} ";
        Map<String, String> args = Map.of("name", "Petr Arsentev", "subject", "you");
        assertThatThrownBy(() -> gs.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNameAndSubjectThenString() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of("name", "Petr Arsentev", "subject", "you");
        assertThat(gs.produce(template, args)).isEqualTo("I am a Petr Arsentev, Who are you? ");
    }
}