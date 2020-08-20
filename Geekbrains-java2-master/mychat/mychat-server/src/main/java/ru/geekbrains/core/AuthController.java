package ru.geekbrains.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.geekbrains.data.User;

import java.util.ArrayList;
import java.util.HashMap;


public class AuthController {

    private static final Logger logger = LogManager.getLogger(AuthController.class);


    HashMap<String, User> users = new HashMap<>();

    public void init() {
        for (User user : receiveUsers()) {
            users.put(user.getLogin(), user);
        }
    }

    public String getNickname(String login, String password) {
        User user = users.get(login);
        if (user != null && user.isPasswordCorrect(password)) {
            return user.getNickname();
        }
        return null;
    }

    private ArrayList<User> receiveUsers() {
        ArrayList<User> usersArr = new ArrayList<>();
        try{
            ChatDB.connect();
            usersArr = ChatDB.getUserList();

        }catch (Exception e) {
            logger.error("Ошибка: {}", e.getMessage(), e);
            e.printStackTrace();
        } finally {
            ChatDB.disconnect();
        }

        return usersArr;
    }

}
