package ru.geekbrains.jdbc;

import java.sql.*;

public class MainJDBCClass {

    /*CREATE TABLE student (
            id    INTEGER PRIMARY KEY AUTOINCREMENT,
            name  TEXT,
            score INTEGER
    );*/

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;


    public static void main(String[] args) {
        try {
            connect();
            dropTable();
            createTable();
            initPrepareStatements();
//            prepExample();
//            performInsertIntoDB();

            statement.executeUpdate("INSERT INTO student (name, score) VALUES ('alex 1', 10);");
            Savepoint savepoint = connection.setSavepoint();
            statement.executeUpdate("INSERT INTO student (name, score) VALUES ('alex 2', 20);");
            connection.rollback(savepoint);
            statement.executeUpdate("INSERT INTO student (name, score) VALUES ('alex 3', 30);");

            connection.commit();
//            readRecords();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        //....

    }

    private static void prepExample() throws SQLException {
        connection.setAutoCommit(false);
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1, "alex " + i);
            preparedStatement.setInt(2, i * 10 % 100);
            preparedStatement.execute();
        }
        connection.commit();
    }

    private static void performInsertIntoDB() throws SQLException {
        connection.setAutoCommit(false);
        for (int i = 0; i < 100_000 ; i++) {
            statement.executeUpdate("INSERT INTO student (name, score) VALUES ('alex " + i + "' , " + i * 10 % 100 + ");");
            if (i % 10000 == 0) {
                connection.commit();
            }
        }
        connection.commit(); // connection.setAutoCommit(true);

    }

    private static void readRecords() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM student");
        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + " "
                    + rs.getString("name") + " "
                    + rs.getInt("score") );
        }
    }

    private static void updateData() throws SQLException {
        statement.executeUpdate("INSERT INTO student (name, score) VALUES ('alex 4', 15);");
        statement.executeUpdate("UPDATE student set score = 55 where id = 1;");
    }

    private static void createTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS student (\n" +
                "            id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "            name  TEXT,\n" +
                "            score INTEGER)" );
    }

    private static void dropTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS student");
    }

    private static void deleteAllRecords() throws SQLException {
        statement.executeUpdate("DELETE FROM student");
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        statement = connection.createStatement();
    }

    public static void initPrepareStatements() throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO student (name, score) VALUES (?, ?)");
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
