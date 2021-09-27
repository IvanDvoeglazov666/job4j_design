package ru.job4j.collection;


import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    private int size = 0;
    private Node<E> first;
    private Node<E> last;
    private int modCount;

    public SimpleLinkedList() {
        this.first = new Node<>(null, null, last);
        this.last = new Node<>(first, null, null);
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }


        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }


    }

    @Override
    public void add(E value) {
        Node<E> l = last;
        l.setItem(value);
        Node<E> newNode = new Node<>(l.prev, null, null);
        last = newNode;
        l.setNext(last);
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Node<E> target = first.getNext();
        for (int i = 0; i < index; i++) {
            target = getNodCurrent(target);
        }
        return target.getItem();
    }

    public Node<E> getNodCurrent(Node<E> current) {
        return current.getNext();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor <= size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return first.getItem();
            }
        };
    }
}
