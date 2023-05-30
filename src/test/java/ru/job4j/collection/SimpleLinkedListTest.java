package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class SimpleLinkedListTest {

    private LinkedList<Integer> list;
    private LinkedList<String> lists;

    @BeforeEach
    public void initData() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
    }

    @BeforeEach
    public void init2Data() {
        lists = new SimpleLinkedList<>();
        lists.add("bmw");
        lists.add("audi");
        lists.add("opel");
    }

    @Test
    void checkIteratorListSimple() {
        assertThat(list).hasSize(2);
        list.add(3);
        list.add(4);
        assertThat(list).hasSize(4);
    }

    @Test
    void checkIteratorListsSimple() {
        assertThat(lists).hasSize(3);
        lists.add("lada");
        assertThat(lists).hasSize(4);
    }

    @Test
    void whenAddAndGet() {
        list.add(3);
        list.add(4);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    void whenListsGet() {
        lists.add("lada");
        assertThat(lists.get(3)).isEqualTo("lada");
        assertThat(lists.get(2)).isEqualTo("opel");
        assertThat(lists.get(1)).isEqualTo("audi");
        assertThat(lists.get(0)).isEqualTo("bmw");
    }

    @Test
    void whenGetFromOutOfBoundThenExceptionThrown() {
        assertThatThrownBy(() -> list.get(2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenListsGetFromOutOfBoundThenExceptionThrown() {
        assertThatThrownBy(() -> lists.get(3))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddIterHasNextTrue() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenAddIterNextOne() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    void whenEmptyIterHashNextFalse() {
        LinkedList<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    void whenAddIterMultiHasNextTrue() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenAddIterNextOneNextTwo() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
    }

    @Test
    void whenGetIteratorTwiceThenEveryFromBegin() {
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(1);
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(2);
        assertThat(first.hasNext()).isFalse();
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(1);
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(2);
        assertThat(second.hasNext()).isFalse();
        Iterator<String> third = lists.iterator();
        assertThat(third.hasNext()).isTrue();
        assertThat(third.next()).isEqualTo("bmw");
        assertThat(third.hasNext()).isTrue();
        assertThat(third.next()).isEqualTo("audi");
        assertThat(third.hasNext()).isTrue();
        assertThat(third.next()).isEqualTo("opel");
        assertThat(third.hasNext()).isFalse();
    }

    @Test
    void whenAddAfterGetIteratorHasNextThenMustBeException() {
        Iterator<Integer> it = list.iterator();
        list.add(3);
        assertThatThrownBy(it::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenGetIteratorFromEmptyListThenNextThrowException() {
        list = new SimpleLinkedList<>();
        assertThatThrownBy(list.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }
}