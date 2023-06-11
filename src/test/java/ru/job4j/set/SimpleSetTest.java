package ru.job4j.set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.collection.SimpleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleSetTest {

    private Set<Integer> set;

    @BeforeEach
    void set() {
        set = new SimpleSet<>();
    }

    @Test
    void whenAddNonNull() {
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenIterator() {
        set.add(1);
        set.add(2);
        set.add(3);
        Iterator<Integer> it = set.iterator();
        assertThat(set).hasSize(3);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(3);
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    void whenIteratorThrowException() {
        Iterator<Integer> it = set.iterator();
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }
}