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

    private static class Node<E> {
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
        Objects.checkIndex(index, size);
        Node<E> target = first.getNextNode();
        for (int i = 0; i < index; i++) {
            target = target.getNextNode();
        }
        return target.getElement();
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
                return get(cursor);
            }
        };
    }
}
