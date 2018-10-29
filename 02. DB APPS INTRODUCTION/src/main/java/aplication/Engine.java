package aplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
            this.changeTownNamesCasing(reader.readLine());
            //comments below are meant for QA department
            //to start task 2 change row above with this: this.getVillainsNames();
            //to start task 3 change row above with this: this.getVillainNameAndMinions(Integer.parseInt(reader.readLine()));
            //to start task 4 change row above with this: this.addMinion();
            //to start task 5 change row above with this: this.changeTownNamesCasing(reader.readLine());
            //to start task 7 change row above with this: this.printAllMinionNames();
            //to start task 8 change row above with this: this.increaseMinionsAge(reader.readLine());
            //to start task 9 change row above with this: this.increaseAgeStoredProcedure(Integer.parseInt(reader.readLine()));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Problem 2. Get Villains' Names
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


        String query = String.format("SELECT v.name, m.name, m.age FROM minions m JOIN minions_villains m2 ON m.id = " +
                "m2.minion_id RIGHT JOIN villains v ON m2.villain_id = v.id WHERE v.id = %d", villainId);
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
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


    /**
     * 4.	Add Minion
     * Write a program that reads information about a minion and its villain and adds it to the database.
     * In case the town of the minion is not in the database, insert it as well.
     * In case the villain is not present in the database, add him too with default evilness factor of “evil”.
     * Finally, set the new minion to be servant of the villain.
     * Print appropriate messages after each operation – see the examples.
     */

    private void addMinion() throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] minionData = reader.readLine().split("\\s+");
        String[] villainData = reader.readLine().split("\\s+");

        String minionName = minionData[1];
        int minionAge = Integer.parseInt(minionData[2]);
        String town = minionData[3];

        String villainName = villainData[1];

        if (!checkIfExistTown(town)) {
            String query = String.format("INSERT INTO towns(name, country) VALUES('%s',NULL)", town);
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.printf("Town %s was added to the database.%n", town);
        }
        if (!checkIfExistVillain(villainName)) {
            String query = String.format("INSERT INTO villains(name, evilness_factor) VALUES('%s','evil')", villainName);
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.printf("Villain %s was added to the database.%n", villainName);
        }
        int townId = this.checkId(town, "towns");

        String query = String.format("INSERT INTO minions(name, age, town_id) VALUES('%s',%d,%d)", minionName, minionAge, townId);
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.executeUpdate();

        int minionID = this.checkId(minionName, "minions");
        int villainID = this.checkId(villainName, "villains");
        String query1 = String.format("INSERT INTO minions_villains(minion_id,villain_id) VALUES(%d,%d)", minionID, villainID);
        PreparedStatement ps = this.connection.prepareStatement(query1);
        ps.executeUpdate();
        System.out.printf("Successfully added %s to be minion of %s%n", minionName, villainName);
    }

    private boolean checkIfExistTown(String columnName) throws SQLException {
        String query = String.format("SELECT * FROM towns WHERE name = '%s'", columnName);
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return false;
        }
        return true;
    }

    private boolean checkIfExistVillain(String villainName) throws SQLException {
        String query = String.format("SELECT * FROM villains WHERE name = '%s'", villainName);
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return false;
        }
        return true;
    }

    private int checkId(String name, String tableName) throws SQLException {
        String query = String.format("SELECT t.id FROM %s t WHERE t.name = '%s'", tableName, name);
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    /**
     * 5.	Change Town Names Casing
     * Write a program that changes all town names to uppercase for a given country.
     * Print the number of towns that were changed in the format provided in examples.
     * On the next line print the names that were changed, separated by coma and a space.
     */

    private void changeTownNamesCasing(String country) throws SQLException {
        List<String> towns = new ArrayList<>();
        String query = String.format("SELECT t.name FROM towns t WHERE t.country = '%s'", country);
        PreparedStatement pr = this.connection.prepareStatement(query);
        ResultSet rs = pr.executeQuery();

        while (rs.next()) {
            String townName = rs.getString("name");
            String queryToUpperCase = String.format("UPDATE towns t SET t.name = '%S' WHERE t.name = '%s'", townName, townName);
            PreparedStatement preparedStatement = this.connection.prepareStatement(queryToUpperCase);
            preparedStatement.executeUpdate();
            towns.add(townName.toUpperCase());
        }

        if (towns.size() == 0) {
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d town names were affected.%n", towns.size());
            System.out.println(towns.toString());
        }
    }

    /**
     * 7.	Print All Minion Names
     * Write a program that prints all minion names from the minions table in order first record, last record,
     * first + 1, last – 1, first + 2, last – 2… first + n, last – n.
     */

    private void printAllMinionNames() throws SQLException {
        List<String> minionsNames = new LinkedList<>();
        String query = "SELECT name FROM minions";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            minionsNames.add(resultSet.getString(1));
        }
        for (int i = 0; i < minionsNames.size(); i++) {
            System.out.printf("%s%n", minionsNames.get(i));
            System.out.printf("%s%n", minionsNames.get(minionsNames.size() - 1 - i));
        }
    }

    /**
     * 8.	Increase Minions Age
     * Read from the console minion IDs, separated by space.
     * Increment the age of those minions by 1 and make their names title case.
     * Finally, print the names and the ages of all minions that are in the database. See the examples below.
     */

    private void increaseMinionsAge(String input) throws SQLException {
        if (input.length() != 0) {
            int[] minionsIds = Arrays.stream(input.split("\\s+")).mapToInt(Integer::parseInt).toArray();

            for (int i = 0; i < minionsIds.length; i++) {
                String query = String.format("SELECT name, age FROM minions WHERE id = %d", minionsIds[i]);
                PreparedStatement preparedStatement = this.connection.prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int minionAge = rs.getInt(2) + 1;
                    String[] minionsName = rs.getString(1).split("\\s+");
                    StringBuilder nameToUpper = new StringBuilder();
                    for (int j = 0; j < minionsName.length; j++) {
                        minionsName[j] = minionsName[j].substring(0, 1).toUpperCase() + minionsName[j].substring(1);
                        nameToUpper.append(minionsName[j]).append(" ");
                    }

                    String queryUpdate = String.format("UPDATE minions SET name = '%s', age = %d WHERE id = %d;",
                            nameToUpper.toString().trim(),
                            minionAge,
                            minionsIds[i]);
                    PreparedStatement pr = this.connection.prepareStatement(queryUpdate);
                    pr.executeUpdate();
                }
            }


            String queryPrint = "SELECT name, age from minions";
            PreparedStatement preparedStatement = this.connection.prepareStatement(queryPrint);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                System.out.printf("%s %d%n", result.getString(1), result.getInt(2));
            }

        } else {
            System.out.println("Not Valid Input!");
        }
    }

    /**
     * 9.	Increase Age Stored Procedure
     * Create a stored procedure usp_get_older (directly in the database using MySQL Workbench or any other similar tool)
     * that receives a minion_id and increases the minion’s years by 1.
     * Write a program that uses that stored procedure to increase the age of a minion,
     * whose id will be given as an input from the console. After that print the name and the age of that minion.
     */

    private void increaseAgeStoredProcedure(int minionId) throws SQLException {
        try {
            this.connection.setAutoCommit(false);
            Statement st = this.connection.createStatement();
            String query = String.format("CALL usp_get_older(%d)", minionId);
            st.execute(query);
            this.connection.commit();
            String print = String.format("SELECT name, age FROM minions WHERE id = %d", minionId);
            PreparedStatement pr = this.connection.prepareStatement(print);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                System.out.printf("%s %d", rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.connection.rollback();
        }
    }
}




