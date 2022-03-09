package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {
        boolean notContain = !contains(value);
        if (notContain) {
            set.add(value);
        }
        return notContain;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T data : set) {
            if (Objects.equals(data, value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

