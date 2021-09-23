package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = Arrays.copyOf(container, size + 1);
            container[size] = value;
            size = size + 1;
        } else {
            container[size] = value;
            size++;
        }
        container = Arrays.copyOf(container, size);
        modCount++;


    }

    @Override
    public T set(int index, T newValue) {
        T rsl = container[index];
        for(T num : container) {
            if(container[index].equals(num)) {
                container[index] = newValue;
            }
        }
        return rsl;
    }

    @Override
    public T remove(int index) {
        T rsl = container[index];
        if (container[index] != null) {
            System.arraycopy(container, index + 1, container, index, container.length - index - 1);
            container = Arrays.copyOf(container, size - 1);
            modCount++;

        }
        return rsl;

    }

    @Override
    public T get(int index) {
        return container[index];
    }

    @Override
    public int size() {
        return container.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int expectedModCount = modCount;
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                if(expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < container.length && container[cursor] != null;
            }

            @Override
            public T next() {
                 if(!hasNext() || modCount == 0) {
                     throw new NoSuchElementException();
                 }

                return container[cursor++];
            }

        };
    }
}
