package customORM.db.base;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface DbContext<T> {
    // this method will execute insert or update command
    boolean persist(T entity) throws IllegalAccessException, SQLException;

    // this method will return all data in table
    List<T> find() throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    // this method will return data in table where condition is true
    List<T> find(String where) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    // this method will return first record from db
    T findFirst() throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    // this method will return first record from db where condition is true
    T findFirst(String where) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    //this method will return record in db where id is equal to the given id
    T findById(long id) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    boolean delete(String where) throws SQLException;
}
