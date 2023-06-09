package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ListUtilsTest {

    private List<Integer> input;
    private List<Integer> inputs;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @BeforeEach
    void setUps() {
        inputs = new ArrayList<>(Arrays.asList(1, 3, 0, 2));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfters() {
        ListUtils.addAfter(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 3, 2);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(inputs, i -> i < 2);
        assertThat(inputs).hasSize(2).containsSequence(3, 2);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.replaceIf(inputs, i -> i < 2, 5);
        assertThat(inputs).hasSize(4).containsSequence(5, 3, 5, 2);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.removeAll(inputs, new ArrayList<>(Arrays.asList(0, 1, 2)));
        assertThat(inputs).hasSize(1).containsSequence(3);
    }
}