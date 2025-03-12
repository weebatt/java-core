package commands;

import models.User;
import utility.ServerCollectionManager;

import java.io.IOException;
import java.io.Serializable;

public class RemoveKey extends ServerCommand implements Serializable {
    public RemoveKey(){
        super("remove_key", "удалить элемент из коллекции по его ключу.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        if (Authorization.list.contains(user.getUserName())) {
            new LoadCollection().executionForResponse(null, user);
            String valueStr = (String) value;
            Long key = Long.parseLong(valueStr);
            if (ServerCollectionManager.group.containsKey(key)) {
                ServerCollectionManager.group.remove(key);
                new Save().executionForResponse(null, user);
                return "Element by given key " + key + " successfully deleted!";
            } else {
                return "The specified key does not exist!";
            }
        } else {
            return "You need to reg or log_in";
        }
    }
}
