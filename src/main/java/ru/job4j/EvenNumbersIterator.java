package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return nextEvenPoint() >= 0;
    }

    @Override
    public Integer next() {
        int i = nextEvenPoint();
        if (i < 0) {
           throw new NoSuchElementException();
        }
        point = i;
        return data[point++];
    }

    private int nextEvenPoint() {
        for (var i = point; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                return i;
            }
        }
        return -1;
    }
}
