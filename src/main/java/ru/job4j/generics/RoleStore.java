package ru.job4j.generics;

public class RoleStore<Role extends Base> implements Store<Role> {

    private final Store<Role> store = new MemStore<>();


    @Override
    public void add(Role model) {
         add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return store.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    @Override
    public Role findById(String id) {
        return store.findById(id);
    }
}
