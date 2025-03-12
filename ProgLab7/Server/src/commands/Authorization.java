package commands;

import daba.DataBaseManager;
import models.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Authorization extends ServerCommand implements Serializable {
    public Authorization(){
        super("register", "авторизация пользователя.");
    }
    public static Set<String> list = new HashSet<>();

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.checkUser(user.getUserName()) && dataBaseManager.checkPassword(user.getUserName(), user.getPassword())){
            if (!list.contains(user.getUserName())){
                System.out.println(user.getUserName() + "\n" + user.getPassword());
                System.out.println(user);
                list.add(user.getUserName());
                System.out.println(list);
                return "You successfully log in!";
            } else {
                return "You already successfully log in!";
            }
        } else {
            return "This user doesn't exist!";
        }
    }
}
