package ru.job4j.list;

import ru.job4j.list.List;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size = 0;

    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        checkArraySize();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = checkedValue(index);
        container[index] = newValue;
        modCount++;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T oldValue = checkedValue(index);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        modCount++;
        size--;
        container[size] = null;
        return oldValue;
    }

    @Override
    public T get(int index) {
       return checkedValue(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int expectedModCount  = modCount;
            int id = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return id < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[id++];
            }

        };
    }

    private T checkedValue(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    private void checkArraySize() {
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length == 0 ? 1 : container.length * 2);
        }
    }
}