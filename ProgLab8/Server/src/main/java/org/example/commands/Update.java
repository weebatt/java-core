package org.example.commands;

import org.example.daba.DataBaseManager;
import org.example.models.StudyGroup;
import org.example.models.User;
import org.example.utility.ServerCollectionManager;

import java.io.IOException;
import java.io.Serializable;

public class Update extends ServerCommand implements Serializable {
    public Update(){
        super("update", "обновить значение элемента коллекции, group_id которого равен заданному.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.checkUser(user.getUserName())) {
            StudyGroup studyGroup = (StudyGroup) value;
            if (ServerCollectionManager.group.containsKey(studyGroup.getGroupId())) {
                studyGroup.setUserName(user.getUserName());
                ServerCollectionManager.group.put(studyGroup.getGroupId(), studyGroup);
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
