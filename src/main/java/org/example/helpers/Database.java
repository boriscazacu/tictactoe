package org.example.helpers;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static final String URL = "jdbc:h2:~/tictac";
    private static final String username = "sa";
    private static final String password = "1234";

    public void create() {
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {
            String sql = "CREATE TABLE users (ID int primary key, name varchar(50))";
            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            System.out.println("Database already exist");
        }

    }

    public void insert(String username1, String username2) {
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {
            String sql = String.format(
                "INSERT INTO users (ID, name) VALUES (1, '%s'), (2, '%s');",
                username1,
                username2
            );

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            update(username1, username2);
        }
    }

    public void update(String username1, String username2) {
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {
            String sql = String.format(
                "UPDATE users set name='%s' WHERE ID=1",
                username1
            );
            String sql2 = String.format(
                "UPDATE users set name='%s' WHERE ID=2",
                username2
            );

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            Statement statement2 = connection.createStatement();
            statement2.executeUpdate(sql2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Integer, String> getNames() {
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {

            String sql = "SELECT * FROM users";
            Map<Integer, String> result = new HashMap<>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                result.put(id, name);
            }

            connection.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
