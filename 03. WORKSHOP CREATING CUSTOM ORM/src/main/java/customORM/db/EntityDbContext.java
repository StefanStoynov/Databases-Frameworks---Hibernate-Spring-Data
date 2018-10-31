package customORM.db;

import customORM.db.base.DbContext;


public class EntityDbContext<T> implements DbContext<T> {

    public boolean persist(T entity) {
        return false;
    }

    public Iterable<T> find(Class<T> table) {
        return null;
    }

    public Iterable<T> find(Class<T> table, String where) {
        return null;
    }

    public T findFirst(Class<T> table) {
        return null;
    }

    public T findFirst(Class<T> table, String where) {
        return null;
    }

    public T findById(long id) {
        return null;
    }
}
