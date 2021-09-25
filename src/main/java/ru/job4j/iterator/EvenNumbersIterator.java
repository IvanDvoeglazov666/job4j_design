package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int cursor;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (data[cursor] % 2 == 1 && data[cursor] != data.length) {
            cursor++;
        }
        return data[cursor] % 2 == 0;

    }

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5, 6};
        for (int i : num) {
            if (num[i] % 2 == 1) {
                System.out.println(num[i]);
            }

        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[cursor++];
    }


}