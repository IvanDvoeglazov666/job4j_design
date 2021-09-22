package ru.job4j.generics;

public class UserStore<User extends Base> implements Store<User> {

    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        User in = findById(id);
        if (in != null) {
            store.add(model);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        User user = findById(id);
        boolean rsl = user != null;
        if (rsl) {
            store.delete(id);
        }

        return rsl;
    }

    @Override
    public User findById(String id) {
        User rsl = store.findById(id);
        return rsl;
    }
}