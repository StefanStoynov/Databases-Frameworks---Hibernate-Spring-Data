package customORM;

import customORM.db.base.DbContext;
import customORM.db.EntityDbContext;
import customORM.entities.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class App {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";


    public static void main(String[] args) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Connection connection = createConnection("soft_uni_simple");
        DbContext<User> usersDbContext = getDbContext(connection, User.class);

        User user = usersDbContext.findFirst();
        user.setLastName("Stamatov");
        usersDbContext.persist(user);

        usersDbContext.find().forEach(System.out::println);


        connection.close();
    }

    private static Connection createConnection(String dbName) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");
        return DriverManager.getConnection(CONNECTION_STRING + dbName + "?autoReconnect=true&useSSL=false", properties);
    }

    private static <T> DbContext<T> getDbContext(Connection connection, Class<T> klass) {
        return new EntityDbContext<T>(connection, klass);
    }
}
