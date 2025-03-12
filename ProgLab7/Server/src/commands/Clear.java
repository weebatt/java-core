package commands;

import daba.DataBaseManager;
import models.User;
import utility.ServerCollectionManager;

import java.io.IOException;
import java.io.Serializable;

public class Clear extends ServerCommand implements Serializable {
    public Clear(){
        super("clear", "очистить коллекцию.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (Authorization.list.contains(user.getUserName())){
            ServerCollectionManager.group.clear();
            new Save().executionForResponse(null, user);
//            dataBaseManager.deleteAllCollectionTable(user.getUserName());
            return "The collection was successfully cleared";
        } else {
            return "You need to reg or log_in";
        }
    }
}
