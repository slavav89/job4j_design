package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void parseArrayLengthIsNull() {
        NameLoad nameLoad = new NameLoad();
        String[] array = {};
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array");
    }

    @Test
    void validateNotSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] array = {"Key:Value"};
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(array[0])
                .hasMessageContaining("symbol");
    }

    @Test
    void validateStartSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] array = {"=KeyValue"};
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(array[0])
                .hasMessageContaining("key");
    }

    @Test
    void validateEndSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] array = {"KeyValue="};
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(array[0])
                .hasMessageContaining("value");
    }
}