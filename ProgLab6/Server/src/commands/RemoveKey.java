package commands;

import utility.ServerCollectionManager;

import java.io.Serializable;

public class RemoveKey extends ServerCommand implements Serializable {
    public RemoveKey(){
        super("remove_key", "удалить элемент из коллекции по его ключу.");
    }

    @Override
    public Object executionForResponse(Object value) {
        String valueStr = (String) value;
        Long key = Long.parseLong(valueStr);
        if (ServerCollectionManager.group.containsKey(key)){
            ServerCollectionManager.group.remove(key);
            new Save().executionForResponse(null);
            return "Element by given key " + key + " successfully deleted!";
        }
        else {
            return "The specified key does not exist!";
        }
    }
}
