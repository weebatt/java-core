package org.example.commands;

import org.example.daba.DataBaseManager;
import org.example.models.User;
import org.example.utility.ServerCollectionManager;

import java.io.IOException;
import java.io.Serializable;

public class RemoveKey extends ServerCommand implements Serializable {
    public RemoveKey(){
        super("remove_key", "удалить элемент из коллекции по его ключу.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.checkUser(user.getUserName())) {
            String valueStr = (String) value;
            Long key = Long.parseLong(valueStr);
            if (ServerCollectionManager.group.containsKey(key)) {
                ServerCollectionManager.group.remove(key);
                new Save().executionForResponse(null, user);
                return "1";
            } else {
                return "0";
            }
        } else {
            return "You need to reg or log_in";
        }
    }
}
