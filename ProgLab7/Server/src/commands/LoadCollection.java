package commands;

import daba.DataBaseManager;
import models.User;
import utility.ServerCollectionManager;

import java.io.IOException;
import java.io.Serializable;

public class LoadCollection extends ServerCommand implements Serializable {
    public LoadCollection(){
        super("load_collection", "выгрузка коллекции из БД.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        if (Authorization.list.contains(user.getUserName())) {
            DataBaseManager dataBaseManager = new DataBaseManager();
            ServerCollectionManager.group = dataBaseManager.readFromDataBase();
            return "Collection successfully load from Database!";
        } else {
            return "You need to reg or log_in";
        }
    }
}
