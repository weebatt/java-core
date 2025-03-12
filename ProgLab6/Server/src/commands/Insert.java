package commands;

import models.StudyGroup;
import utility.ServerCollectionManager;

import java.io.Serializable;
import java.util.Map;

public class Insert extends ServerCommand implements Serializable {
    public Insert(){
        super("insert", "добавить новый элемент с заданным ключом.");
    }

    @Override
    public Object executionForResponse(Object value) {
        ServerCollectionManager collectionManager = new ServerCollectionManager();
        StudyGroup studyGroup = (StudyGroup) value;
        collectionManager.addGroupId();
        studyGroup.setGroupId(collectionManager.takeGroupId());
        ServerCollectionManager.group.put(collectionManager.takeGroupId(), studyGroup);

        Map.Entry<Long, StudyGroup> lastEntry = null;
        for (Map.Entry<Long, StudyGroup> entry: ServerCollectionManager.group.entrySet()){
            lastEntry = entry;
        }
        return "{" + lastEntry + "}";
    }
}
