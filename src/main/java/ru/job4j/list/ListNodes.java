package ru.job4j.list;

public interface ListNodes<E> extends Iterable<E> {
    void add(E value);
    E get(int index);
}
