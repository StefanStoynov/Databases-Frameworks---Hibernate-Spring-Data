package customORM.db.base;


public interface DbContext<T> {
    // this method will execute insert or update command
    boolean persist(T entity);

    // this method will return all data in table
    Iterable<T> find(Class<T> table);

    // this method will return data in table where condition is true
    Iterable<T> find(Class<T> table, String where);

    // this method will return first record from db
    T findFirst(Class<T> table);

    // this method will return first record from db where condition is true
    T findFirst(Class<T> table, String where);

    //this method will return record in db where id is equal to the given id
    T findById(long id);
}
