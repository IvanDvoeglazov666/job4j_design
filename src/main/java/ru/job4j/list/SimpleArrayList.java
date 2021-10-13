package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private T[] arrayAdd(T[] array) {
        if (container.length == 0) {
            container = (T[]) new Object[2];
        }
        return Arrays.copyOf(array, size * 2);
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            arrayAdd(container);
            container = arrayAdd(container);
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
        Objects.checkIndex(index, size);
        T rsl = container[index];
        for (T num : container) {
            if (container[index].equals(num)) {
                container[index] = newValue;
                break;
            }
        }
        return rsl;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
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
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return container.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private final int expectedModCount = modCount;
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < container.length && container[cursor] != null;
            }

            @Override
            public T next() {
                if (!hasNext() || modCount == 0) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }
}
