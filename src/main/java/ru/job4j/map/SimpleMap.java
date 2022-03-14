package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (((float) count / capacity) >= LOAD_FACTOR) {
            expand();
        }
        int id = indexFor(key);
        if (table[id] == null) {
            table[id] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private int indexFor(K key) {
        return indexFor(hash(key.hashCode()));
    }

    private void expand() {
        capacity = capacity + capacity * 2;
        MapEntry<K, V>[] tmp = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> point : tmp) {
            if (point == null) {
                continue;
            }
            int id = indexFor(point.key);
            table[id] = point;
        }
    }

    @Override
    public V get(K key) {
       V rsl = null;
       if (key != null) {
           MapEntry<K, V> point = table[indexFor(key)];
           if (point != null
                   && point.key.equals(key)) {
               rsl = point.value;
           }
       }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (key != null) {
            int id = indexFor(key);
            if (table[id] != null
                    && table[id].key.equals(key)) {
                table[id] = null;
                rsl = true;
                count--;
                modCount--;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            int itIndex = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean rsl = false;
                for (int i = itIndex; i < table.length; i++) {
                    if (table[i] != null) {
                        rsl = true;
                        itIndex = i;
                        break;
                    }
                }
                return rsl;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                 throw new NoSuchElementException();
                }
                return table[itIndex++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof MapEntry)) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return key.equals(mapEntry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}
