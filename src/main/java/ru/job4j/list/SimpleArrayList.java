package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements ListArray<T> {

    private T[] container;

    private int size = 0;

    private int modCount = 0;

    public SimpleArrayList() {
        this.container = (T[]) new Object[1];
    }

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
        T oldValue = get(index);
        container[index] = newValue;
        modCount++;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        modCount++;
        size--;
        container[size] = null;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
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

    private void checkArraySize() {
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length == 0 ? 1 : container.length * 2);
        }
    }
}