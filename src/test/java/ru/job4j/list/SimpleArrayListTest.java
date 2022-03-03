package ru.job4j.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class SimpleArrayListTest {

    ListArray<Integer> listArray;

    @Before
    public void initData() {
        listArray = new SimpleArrayList<>(3);
        listArray.add(1);
        listArray.add(2);
        listArray.add(3);
    }

    @Test
    public void whenAddThenSizeIncrease() {
        Assert.assertEquals(3, listArray.size());
    }

    @Test
    public void whenAddAndGetByCorrectIndex() {
        Assert.assertEquals(Integer.valueOf(1), listArray.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAndGetByIncorrectIndexThenGetException() {
        listArray.get(5);
    }

    @Test
    public void whenRemoveThenGetValueAndSizeDecrease() {
        Assert.assertEquals(3, listArray.size());
        Assert.assertEquals(Integer.valueOf(1), listArray.remove(0));
        Assert.assertEquals(2, listArray.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveByIncorrectIndexThenGetException() {
        listArray.remove(5);
    }

    @Test
    public void whenRemoveThenMustNotBeEmpty() {
        listArray.remove(1);
        Assert.assertEquals(Integer.valueOf(1), listArray.get(0));
        Assert.assertEquals(Integer.valueOf(3), listArray.get(1));
    }

    @Test
    public void whenRemoveThenMustNotBeEmpty4() {
        listArray.add(4);
        listArray.remove(2);
        Assert.assertEquals(Integer.valueOf(1), listArray.get(0));
        Assert.assertEquals(Integer.valueOf(4), listArray.get(2));
    }


    @Test
    public void whenAddNullThenMustBeSameBehavior() {
        listArray = new SimpleArrayList<>(1);
        listArray.add(null);
        listArray.add(null);
        Assert.assertEquals(2, listArray.size());
        Assert.assertNull(listArray.get(0));
        Assert.assertNull(listArray.get(1));
    }

    @Test
    public void whenSetThenGetOldValueAndSizeNotChanged() {
        Assert.assertEquals(Integer.valueOf(2), listArray.set(1, 22));
        Assert.assertEquals(3, listArray.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetByIncorrectIndexThenGetException() {
        listArray.set(5, 22);
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        listArray = new SimpleArrayList<>(5);
        Assert.assertFalse(listArray.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        listArray = new SimpleArrayList<>(5);
        listArray.iterator().next();
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        Assert.assertEquals(Integer.valueOf(1), listArray.iterator().next());
        Assert.assertEquals(Integer.valueOf(1), listArray.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = listArray.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void whenNoPlaceThenMustIncreaseCapacity() {
        IntStream.range(3, 10).forEach(v -> listArray.add(v));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = listArray.iterator();
        listArray.add(4);
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = listArray.iterator();
        listArray.add(0);
        iterator.next();
    }

}