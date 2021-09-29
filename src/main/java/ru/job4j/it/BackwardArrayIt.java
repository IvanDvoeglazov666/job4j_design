package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {

    private final int[] data;
    private int point;

    public BackwardArrayIt(int[] data) {
        this.point = data.length - 1;
        this.data = data;
    }

    @Override
    public boolean hasNext() {
         return point >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }
}
