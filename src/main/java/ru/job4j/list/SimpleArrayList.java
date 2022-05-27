package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void arrayAdd(T[] array) {
        if (container.length == 0) {
            container = (T[]) new Object[2];
        } else {
            container = Arrays.copyOf(array, size * 2);
        }
    }


    @Override
    public void add(T value) {
        if (size == container.length) {
            arrayAdd(container);
            container[size] = value;
            size++;
        } else {
            container[size] = value;
            size = size + 1;
        }
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T rsl = container[index];
        container[index] = newValue;
        modCount++;
        return rsl;
    }

    @Override
    public T remove(int index) {
        T rsl = container[index];
        if (container[index] != null) {
            System.arraycopy(container, index + 1, container, index, container.length - index - 1);
            container[container.length - 1] = null;
            size--;
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
        return size;
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
                return container[cursor] != null;
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
