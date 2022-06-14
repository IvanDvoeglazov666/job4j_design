package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<Integer> implements Iterable<Integer> {

    private Node<Integer> head;

    public void add(Integer value) {
        Node<Integer> node = new Node<Integer>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<Integer> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public Integer deleteFirst() {
        Node<Integer> first = head;
        if (first == null) {
            throw new NoSuchElementException();
        }
        head = first.next;
        first.next = null;
        return first.value;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            Node<Integer> node = head;

            @Override
            public boolean hasNext() {
                if (head == null) {
                    throw new NoSuchElementException();
                }
                return node != null;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Integer value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}