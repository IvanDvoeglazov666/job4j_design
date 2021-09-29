package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCont;

    public SimpleLinkedList() {
        this.last = new Node<>(first, null, null);
        this.first = new Node<>(null, null, last);

    }

    public static class Node<E> {
        private E element;
        private final Node<E> prevNode;
        private Node<E> nextNode;

        private Node(Node<E> prevNode, E element, Node<E> nextNode) {
            this.element = element;
            this.prevNode = prevNode;
            this.nextNode = nextNode;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }

    @Override
    public void add(E value) {
        Node<E> l = last;
        l.setElement(value);
        Node<E> newNode = new Node<>(l, null, null);
        last = newNode;
        l.setNextNode(last);
        size++;
        modCont++;
    }

    @Override
    public E get(int index) {

        if (Objects.checkIndex(index, size) >= size) {
            throw new IndexOutOfBoundsException("index greater than length");
        }
        Node<E> target = first.getNextNode();
        for (int i = 0; i < index; i++) {
            target = getNext(target);
        }
        return target.getElement();
    }

    public Node<E> getNext(Node<E> df) {
        return df.getNextNode();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCont;
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCont) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(cursor++);
            }
        };
    }
}
