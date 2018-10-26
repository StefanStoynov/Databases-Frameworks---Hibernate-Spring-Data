package aplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Engine implements Runnable {

    private Connection connection;

    public Engine(Connection connection) {
        this.connection = connection;
    }


    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            this.getVillainNameAndMinions(Integer.parseInt(reader.readLine()));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Problem 1. Get Villains' Names
     * Write a program that prints on the console all villains’ names and their number of minions.
     * Get only the villains who have more than 3 minions. Order them by number of minions in descending order.
     */
    private void getVillainsNames() throws SQLException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String query = "SELECT v.name, count(mv.minion_id) FROM villains v JOIN minions_villains mv  ON v.id = mv.villain_id GROUP BY v.name HAVING count(mv.minion_id)> ? ORDER BY count(mv.minion_id) DESC";

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1, Integer.parseInt(reader.readLine()));

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            System.out.printf("%s %d%n", resultSet.getString(1), resultSet.getInt(2));
        }

    }

    /**
     * 3.	Get Minion Names
     * Write a program that prints on the console all minion names and their age for given villain id.
     * For the output, use the formats given in the examples.
     */

    private void getVillainNameAndMinions(int villainId) throws SQLException {


        String query = String.format("SELECT v.name, m.name, m.age FROM minions m JOIN minions_villains m2 ON m.id = m2.minion_id RIGHT JOIN villains v ON m2.villain_id = v.id WHERE v.id = %d", villainId);
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.first()) {
            System.out.printf("No villain with ID %d exists in the database.", villainId);
        } else if (resultSet.getString(2) == null) {
            System.out.printf("Villain: %s", resultSet.getString(1));
        } else {
            System.out.printf("Villain: %s%n", resultSet.getString(1));
            int index = 1;
            do {
                System.out.printf("%d. %s %d%n", index, resultSet.getString(2), resultSet.getInt(3));
                index++;
            } while (resultSet.next());
        }
    }
}



