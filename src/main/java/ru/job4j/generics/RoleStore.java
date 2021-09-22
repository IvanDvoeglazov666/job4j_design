package ru.job4j.generics;

public class RoleStore<Role extends Base> implements Store<Role> {

    private final Store<Role> store = new MemStore<>();

    @Override
    public void add(Role model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        Role in = findById(id);
        if (in != null) {
            store.add(model);
        }
        return false;

    }

    @Override
    public boolean delete(String id) {
        Role role = findById(id);
        boolean rsl = role != null;
        if (rsl) {
            store.delete(id);
        }

        return rsl;
    }

    @Override
    public Role findById(String id) {
        Role rsl = store.findById(id);
        return rsl;
    }
}
