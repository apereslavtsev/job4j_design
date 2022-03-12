package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleMapTest {

    @Test
    public void expand() {
        SimpleMap<Integer, Integer> map = new SimpleMap();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.put(6, 6);
        map.put(7, 7);
        map.put(8, 8);
        assertTrue(map.getCapacity() > 8);
    }

    @Test
    public void whenPutOk() {
        SimpleMap<String, Integer> map = new SimpleMap();
        map.put("one", 1);
        map.put("two", 2);
        Iterator<String> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is("two"));
        assertThat(iterator.next(), is("one"));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenPutCollision() {
        SimpleMap<Integer, Integer> map = new SimpleMap();
        map.put(88888, 1);
        map.put(88888, 2);
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(88888));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenGetOk() {
        SimpleMap<String, Integer> map = new SimpleMap();
        map.put("one", 1);
        map.put("two", 2);
        assertThat(map.get("one"), is(1));
        assertThat(map.get("two"), is(2));
    }

    @Test
    public void whenGetNull() {
        SimpleMap<Integer, Integer> map = new SimpleMap();
        map.put(88888, 1);
        map.put(88888, 2);
        assertThat(map.get(88888), is(1));
        assertNull(map.get(15));
    }

    @Test
    public void whenRemoveOk() {
        SimpleMap<String, Integer> map = new SimpleMap();
        map.put("one", 1);
        map.put("two", 2);
        assertTrue(map.remove("one"));
        assertTrue(map.remove("two"));
        assertFalse(map.iterator().hasNext());
    }

    @Test
    public void whenRemoveWrong() {
        SimpleMap<String, Integer> map = new SimpleMap();
        map.put("one", 1);
        map.put("two", 2);
        assertTrue(map.remove("one"));
        assertFalse(map.remove("one"));
        var it = map.iterator();
        assertThat(it.next(), is("two"));
        assertFalse(it.hasNext());
    }

}