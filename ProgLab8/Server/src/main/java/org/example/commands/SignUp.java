package org.example.commands;

import org.example.daba.DataBaseManager;
import org.example.models.User;

import java.io.IOException;
import java.io.Serializable;

public class SignUp extends ServerCommand implements Serializable {
    public SignUp(){
        super("register", "регистрация пользователя.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.checkUser(user.getUserName())){
            return "0";
        } else {
            dataBaseManager.registerUser(user.getUserName(), user.getPassword());
            return "1";
        }
    }
}
