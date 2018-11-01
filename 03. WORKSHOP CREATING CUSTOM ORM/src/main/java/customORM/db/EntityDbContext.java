package customORM.db;

import customORM.db.annotations.Entity;
import customORM.db.annotations.Column;
import customORM.db.annotations.PrimaryKey;
import customORM.db.base.DbContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class EntityDbContext<T> implements DbContext<T> {
    private static final String SELECT_QUERY_TEMPLATE = "SELECT * FROM {0}";
    private static final String SELECT_WHERE_QUERY_TEMPLATE = "SELECT * FROM {0} WHERE {1}";
    private static final String SELECT_SINGLE_QUERY_TEMPLATE = "SELECT * FROM {0} {1} LIMIT 1";
    private static final String SELECT_SINGLE_WHERE_QUERY_TEMPLATE = "SELECT * FROM {0} WHERE {1} LIMIT 1";
    private static final String WHERE_BY_ID_TEMPLATE = "{0} = {1}";
    private final Connection conection;
    private final Class<T> klass;

    public EntityDbContext(Connection connection, Class<T> klass) {
        this.conection = connection;
        this.klass = klass;
    }

    @Override
    public boolean persist(T entity) {
        return false;
    }

    @Override
    public List<T> find() throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return find(null);
    }
    
    @Override
    public List<T> find(String where) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        String queryTemplate = where == null
                ? SELECT_QUERY_TEMPLATE
                : SELECT_WHERE_QUERY_TEMPLATE;

        return find(queryTemplate, where);
    }

    @Override
    public T findFirst() throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return findFirst(null);
    }

    @Override
    public T findFirst(String where) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String queryTemplate = where == null
                ? SELECT_SINGLE_QUERY_TEMPLATE
                : SELECT_SINGLE_WHERE_QUERY_TEMPLATE;

        return find(queryTemplate,where).get(0);
    }

    @Override
    public T findById(long id) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String primaryKeyName = getPrimaryKeyField().getAnnotation(PrimaryKey.class).name();
        String whereString = MessageFormat.format(WHERE_BY_ID_TEMPLATE,primaryKeyName,id);

        return findFirst(whereString);
    }

    private List<T> toList(ResultSet resultSet) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<T> result = new ArrayList<>();
        while (resultSet.next()) {
            T entity = this.createEntity(resultSet);
            result.add(entity);
        }
        return result;
    }

    private T createEntity(ResultSet resultSet) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, SQLException {
        T entity = klass.getConstructor().newInstance();

        List<Field> columnFields = Arrays.stream(klass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

        Field primaryKeyField = getPrimaryKeyField();

        primaryKeyField.setAccessible(true);
        primaryKeyField.set(entity, resultSet.getLong(primaryKeyField.getAnnotation(PrimaryKey.class).name()));

        columnFields.stream().forEach(field -> {
            String columnName = field.getAnnotation(Column.class).name();
            try {
                field.setAccessible(true);
                if (field.getType() == int.class ||
                        field.getType() == long.class ||
                        field.getType() == Integer.class ||
                        field.getType() == Long.class) {

                    long value = resultSet.getLong(columnName);
                    field.set(entity, value);
                } else if (field.getType() == String.class) {
                    String value = resultSet.getString(columnName);
                    field.set(entity, value);
                }
            } catch (SQLException | IllegalAccessException e) {
                e.printStackTrace();
            }

        });

        return entity;
    }

    private Field getPrimaryKeyField() {
        return Arrays.stream(klass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(PrimaryKey.class))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Class" + klass + "does not have a primary key annotation!"));
    }

    private String getTableName() {
        Annotation annotation = Arrays.stream(klass.getAnnotations())
                .filter(a -> a.annotationType() == Entity.class)
                .findFirst()
                .orElse(null);
        if (annotation == null) {
            return klass.getSimpleName().toLowerCase() + "s";
        }
        return klass.getAnnotation(Entity.class).name();
    }

    private List<T> find(String template, String where) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        String query = MessageFormat.format(template, getTableName(), where);

        PreparedStatement preparedStatement = this.conection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        return toList(resultSet);
    }
}
