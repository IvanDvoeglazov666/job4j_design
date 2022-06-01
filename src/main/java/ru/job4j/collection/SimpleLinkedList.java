package ru.job4j.collection;

import org.w3c.dom.Node;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCount;

    public E node(int index) {
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
        return node(index);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int expectedModCount = modCount;
            private int cursor = 0;


            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext() || modCount == 0) {
                    throw new NoSuchElementException();
                }
                SimpleLinkedList.Node<E> current = first;
                for (int a = 0; a < cursor; a++) {
                    while (current.next != null) {
                        current = current.next;

                    }
                }
                cursor++;
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
