package ru.job4j.collection;


import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
    private final E[] container;
    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCount;
    private int length = 2;

    public SimpleLinkedList() {
        this.container = (E[]) new Object[length];
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }


    @Override
    public void add(E value) {
        length++;
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
            container[size] = value;
        } else {
            l.next = newNode;
            container[size] = value;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        E rsl = null;
        if (index != -1) {
            Objects.checkIndex(index, container.length);
            rsl = container[index];
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int cursor;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                      throw new ConcurrentModificationException();
                }
                return cursor < container.length && container[cursor] != null;
            }

            @Override
            public E next() {
                if (!hasNext() || modCount == 0) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }
}
