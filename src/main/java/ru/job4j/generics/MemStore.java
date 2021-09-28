package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    private String indexOf(String id) {
        String rsl = null;
        if (mem.get(id) != null) {
                rsl = id;
        }
        return rsl;
    }

    @Override
    public boolean replace(String id, T model) {
        String i = indexOf(id);
        boolean rsl = i != null;
        if (rsl) {
            mem.replace(id, model);
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        String i = indexOf(id);
        boolean rsl = i != null;
        if (rsl) {
            mem.remove(id);
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        String rsl = null;
        if (mem.get(id) != null) {
            rsl = id;
        }
        return mem.get(rsl);
    }
}