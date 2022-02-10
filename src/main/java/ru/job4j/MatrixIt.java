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
        /* miss empty rows*/
        while (data[row].length == 0
                && row < data.length - 1) {
            row++;
        }
        return row < data.length
                && column < data[row].length;
    }

    @Override
    public Integer next() {
        int result;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column == data[row].length - 1) {
            result = data[row++][column];
            column = 0;
        } else {
            result = data[row][column++];
        }
        return result;
    }
}
