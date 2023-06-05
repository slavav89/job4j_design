package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class RevertLinkedTest {
    private RevertLinked<Integer> linked;

    @BeforeEach
    void init() {
        linked = new RevertLinked<>();
    }

    @Test
    void whenSize0ThenReturnFalse() {
        assertThat(linked.revert()).isFalse();
    }

    @Test
    void whenSize1ThenReturnFalse() {
        linked.add(1);
        assertThat(linked.revert()).isFalse();
    }

    @Test
    void whenAddAndRevertTrue() {
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
        assertThat(linked).containsSequence(1, 2, 3, 4);
        assertThat(linked.revert()).isTrue();
        assertThat(linked).containsSequence(4, 3, 2, 1);
    }

    @Test
    void whenIteratorThenNextExceptionThrown() {
        Iterator<Integer> it = linked.iterator();
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenIteratorThenHasNextExceptionThrown() {
        linked.add(1);
        Iterator<Integer> it = linked.iterator();
        linked.add(2);
        assertThatThrownBy(it::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);

    }
}