package ru.geekbrains.core;

/*
* 1. Добавить в сетевой чат авторизацию через базу данных SQLite.
* 2.*Добавить в сетевой чат возможность смены ника.
* */

import ru.geekbrains.data.User;

import java.sql.*;
import java.util.ArrayList;

public class ChatDB {

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    public static void main(String[] args) {
        try {
            connect();
//            createTable();
//            insertUsers("admin", "admin", "sysroot");
//            insertUsers("alex", "123", "alex-st");
            readRecords();
//            connection.commit();
//
        } catch (Exception e) {
        } finally {
            disconnect();
        }
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:chat.db");
        statement = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void createTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (\n" +
                "            id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "            login  TEXT,\n" +
                "            password TEXT, \n" +
                "            nickname TEXT)");
    }

    private static void insertUserPrepareStatements() throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO users (login, password, nickname) VALUES (?, ?, ?)");
    }

    private static void updateNicknamePrepareStatements() throws SQLException{
        preparedStatement = connection.prepareStatement("UPDATE users SET nickname = ? where login = ?");
    }

    public static void insertUsers(String login, String password, String nickname) throws SQLException {
        connection.setAutoCommit(false);
        insertUserPrepareStatements();

        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, nickname);
        preparedStatement.execute();

        connection.commit();
    }

    private static void readRecords() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM users");
        while (rs.next()) {
            System.out.println(
                    rs.getString(1) + " "
                            + rs.getString("login") + " "
                            + rs.getString("password") + " "
                            + rs.getString("nickname"));
        }
    }

    public static ArrayList<User> getUserList () throws SQLException {
        ArrayList<User> usersArr = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT * FROM users");
        while (rs.next()){
            usersArr.add(new User(rs.getString("login"), rs.getString("password"), rs.getString("nickname")));
        }
        return usersArr;
    }

    public static void updateNickname(String login, String nickname) throws SQLException {
        connection.setAutoCommit(false);
        updateNicknamePrepareStatements();

        preparedStatement.setString(1, nickname);
        preparedStatement.setString(2, login);
        preparedStatement.execute();

        connection.commit();
    }

    private static void dropTable(String table) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS " + table);
    }

    private static void deleteAllRecords() throws SQLException {
        statement.executeUpdate("DELETE FROM users");
    }



}
