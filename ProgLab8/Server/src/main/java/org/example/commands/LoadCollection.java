package org.example.commands;

import org.example.daba.DataBaseManager;
import org.example.models.User;
import org.example.utility.ServerCollectionManager;

import java.io.IOException;
import java.io.Serializable;

public class LoadCollection extends ServerCommand implements Serializable {
    public LoadCollection(){
        super("load_collection", "выгрузка коллекции из БД.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.checkUser(user.getUserName())) {
            ServerCollectionManager.group = dataBaseManager.readFromDataBase();
            return ServerCollectionManager.group;
        } else {
            return "0";
        }
    }
}
