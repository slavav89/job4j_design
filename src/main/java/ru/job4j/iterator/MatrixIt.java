package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (data[row].length == 0) {
            if (row == data.length - 1) {
                return false;
            }
            column = 0;
            row++;
        }
        return column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer i = 0;
        if (column == data[row].length - 1 && row == data.length - 1) {
            i = data[row][column];
        } else if (column == data[row].length - 1 && row < data.length - 1) {
            i = data[row++][column];
        } else if (column < data[row].length - 1) {
            i = data[row][column++];
        }
        return i;
    }
}