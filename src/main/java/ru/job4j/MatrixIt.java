package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        missEmptyRows();
        return row < data.length
                && column < data[row].length;
    }

    @Override
    public Integer next() {
        if (column == data[row].length - 1) {
            row++;
            column = 0;
        } else {
            column++;
        }

        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column];
    }

    private void missEmptyRows() {
        while (data[row].length == 0
                && row < data.length - 1) {
            row++;
        }
    }

}
