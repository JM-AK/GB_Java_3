package ru.geekbrains.ref.orm;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;

public class Orm {

    private static Connection connection;
    private static Statement statement;



    public static void main(String[] args) {
        try {
            connect();
            createTable(Human.class);
            Human human = new Human(1, "Ivan", "Ivanov", 30, 150000);
            insertRecord(human);

//            createTable(Cat.class);
//            Cat cat = new Cat(1, "Kot", 10, "kot-nick");
//            insertRecord(cat);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void createTable(Class clazz) throws SQLException {
        if (!clazz.isAnnotationPresent(TableXT.class)) {
            return;
        }
        String tableName = ((TableXT)clazz.getAnnotation(TableXT.class)).table();
        statement.execute("DROP TABLE IF EXISTS " + tableName);

        HashMap<Class, String> mapper = new HashMap<>();
        mapper.put(int.class, "INTEGER");
        mapper.put(String.class, "TEXT");

        String query = "CREATE TABLE " + tableName + " (";
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(FieldXT.class)) {
                query += field.getName() + " " + mapper.get(field.getType()) + ", ";
            }
        }
        query = query.replaceAll(", $", ");");
        statement.execute(query);
    }

    // INSERT INTO cats  (id, name, age) VALUES (1, 'kot', 10)
    public static void insertRecord (Object object) throws SQLException, IllegalAccessException {
        Class clazz = object.getClass();
        if (!clazz.isAnnotationPresent(TableXT.class)) {
            return;
        }
        String tableName = ((TableXT)clazz.getAnnotation(TableXT.class)).table();
        String query = "INSERT INTO " + tableName + " (";
        // INSERT INTO cats (
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(FieldXT.class)) {
                query += field.getName() + ", ";
            }
        }
        // INSERT INTO cats (id, name, age,
        query = query.replaceAll(", $", ") VALUES (");
        // INSERT INTO cats (id, name, age) VALUES (
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(FieldXT.class)) {
                query += "?, ";
            }
        }
        // INSERT INTO cats (id, name, age) VALUES (?, ?, ?,
        query = query.replaceAll(", $", ");");
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int counter = 1;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(FieldXT.class)) {
                field.setAccessible(true);
                preparedStatement.setObject(counter, field.get(object));
                counter++;
            }
        }
        preparedStatement.execute();
    }


    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        statement = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}
