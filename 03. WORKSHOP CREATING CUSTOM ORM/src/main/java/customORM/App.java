package customORM;

import customORM.db.base.DbContext;
import customORM.db.EntityDbContext;
import customORM.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class App {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";

    public static void main(String[] args) throws SQLException {
        Connection connection = createConnection("soft_uni_simple");
        DbContext<User> usersDbContext = getDbContext(connection);
    }

    private static Connection createConnection(String dbName) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");
        return DriverManager.getConnection(CONNECTION_STRING + dbName, properties);
    }

    private static <T> DbContext <T> getDbContext(Connection connection){
        return new EntityDbContext();
    }
}
