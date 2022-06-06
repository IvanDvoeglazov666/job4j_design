package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCount;


    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(last, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> i = first;
        Node<E> x = last;
        Node<E> rsl = null;
        if (index == 0) {
            rsl = i;
            return rsl.item;
        } else if (index == (size - 1)) {
            rsl = x;
            return rsl.item;
        } else if (index > 0) {
            first.next = i;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int expectedModCount = modCount;
            private int num = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return size > num;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                SimpleLinkedList.Node<E> current = first;
                while ((current.next != null) && num > 0) {
                    current = current.next;
                }
                num++;
                return current.item;
            }
        };
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
}
