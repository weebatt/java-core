package org.example.commands;

import org.example.daba.DataBaseManager;
import org.example.models.User;

import java.io.IOException;
import java.io.Serializable;

public class LogIn extends ServerCommand implements Serializable {
    public LogIn(){
        super("log_in", "авторизация пользователя.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.checkUser(user.getUserName()) && dataBaseManager.checkPassword(user.getUserName(), user.getPassword())){
            return "1";
        } else {
            return "0";
        }
    }
}
