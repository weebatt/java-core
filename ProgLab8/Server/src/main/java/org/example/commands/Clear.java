package org.example.commands;

import org.example.daba.DataBaseManager;
import org.example.models.StudyGroup;
import org.example.models.User;
import org.example.utility.ServerCollectionManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class Clear extends ServerCommand implements Serializable {
    public Clear(){
        super("clear", "очистить коллекцию.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.checkUser(user.getUserName())){
            for (Map.Entry<Long, StudyGroup> item : ServerCollectionManager.group.entrySet()){
                if (item.getValue().getUserName().equals(user.getUserName())){
                    ServerCollectionManager.group.remove(item.getKey());
                }
            }
            new Save().executionForResponse(null, user);
            return "1";
        } else {
            return "0";
        }
    }
}
