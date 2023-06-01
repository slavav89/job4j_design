package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        Node<T> newNode = new Node<>(value, null);
        Node<T> node = head;
        if (size == 0) {
            head = newNode;
        } else {
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }
        modCount++;
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        final T element = head.item;
        final Node<T> node = head.next;
        head.item = null;
        head.next = null;
        head = node;
        size--;
        modCount++;
        return element;
    }

    public void addFirst(T value) {
        Node<T> node = head;
        head = new Node<T>(value, node);
        modCount++;
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private final int expectedModCount = modCount;
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T res = node.item;
                node = node.next;
                return res;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}