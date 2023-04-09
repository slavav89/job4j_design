package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;
    private int evenIndex = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean b = false;
        while (index < data.length) {
            if (data[index] % 2 == 0) {
                evenIndex = index;
                b = true;
                break;
            } else {
                index++;
            }
        }
        return b;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = evenIndex;
        return data[index++];
    }
}