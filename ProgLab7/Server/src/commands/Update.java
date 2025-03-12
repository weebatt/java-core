package commands;

import models.StudyGroup;
import models.User;
import utility.ServerCollectionManager;

import java.io.IOException;
import java.io.Serializable;

public class Update extends ServerCommand implements Serializable {
    public Update(){
        super("update", "обновить значение элемента коллекции, group_id которого равен заданному.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        if (Authorization.list.contains(user.getUserName())) {
            new LoadCollection().executionForResponse(null, user);
            StudyGroup studyGroup = (StudyGroup) value;
            if (ServerCollectionManager.group.containsKey(studyGroup.getGroupId())) {
                ServerCollectionManager.group.put(studyGroup.getGroupId(), studyGroup);
                new Save().executionForResponse(null, user);
                return "Collection element data given group_id " + studyGroup.getGroupId() + " successfully updated!";
            } else {
                return "The specified group_id does not exist!";
            }
        } else {
            return "You need to reg or log_in";
        }
    }
}
