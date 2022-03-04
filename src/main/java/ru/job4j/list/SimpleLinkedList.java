package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class SimpleLinkedList<E> implements ListNodes<E> {
    private Node<E> first;

    private Node<E> last;

    private Node<E> node;

    private int size = 0;

    private int modCount = 0;

    @Override
    public void add(E value) {
        node = new Node<>(last, value, null);
        if (last != null) {
            last.next = node;
        }
        last = node;
        if (size++ == 0) {
            first = node;
        }
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            private Node<E> currentNode = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E item = currentNode.item;
                currentNode = currentNode.next;
                return item;
            }
        };

    }
}
