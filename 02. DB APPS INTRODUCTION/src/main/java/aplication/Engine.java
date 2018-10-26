package aplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Engine implements Runnable {

    private Connection connection;

    public Engine(Connection connection) {
        this.connection = connection;
    }


    public void run() {
        try {
            this.getVillainsNames();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Problem 1. Get Villains' Names
     */
    private void getVillainsNames() throws SQLException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String query = "SELECT v.name, count(mv.minion_id) FROM villains v JOIN minions_villains mv  ON v.id = mv.villain_id GROUP BY v.name HAVING count(mv.minion_id)> ? ORDER BY count(mv.minion_id) DESC";

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1,Integer.parseInt(reader.readLine()));

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){

            System.out.printf("%s %d%n",resultSet.getString(1),resultSet.getInt(2));
        }

    }
}
