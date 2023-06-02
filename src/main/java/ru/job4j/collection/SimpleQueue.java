package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    int countIn = 0;
    int countOut = 0;

    public T poll() {
        if (countIn == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        int simpleCountOut = countOut;
        if (simpleCountOut == 0) {
            while (countIn != 0) {
                out.push(in.pop());
                countIn--;
                countOut++;
            }
        }
        T element = out.pop();
        countOut--;
        return element;
    }

    public void push(T value) {
        in.push(value);
        countIn++;
    }
}