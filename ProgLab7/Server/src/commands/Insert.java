package commands;

import daba.DataBaseManager;
import models.*;
import utility.ServerCollectionManager;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

public class Insert extends ServerCommand implements Serializable {
    public Insert(){
        super("insert", "добавить новый элемент с заданным ключом.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws SQLException {
        if (Authorization.list.contains(user.getUserName())) {
            ServerCollectionManager collectionManager = new ServerCollectionManager();
            DataBaseManager dataBaseManager = new DataBaseManager();
            StudyGroup studyGroup = (StudyGroup) value;
            collectionManager.addGroupId();
            studyGroup.setGroupId(collectionManager.takeGroupId());
            ServerCollectionManager.group.put(collectionManager.takeGroupId(), studyGroup);

            Map.Entry<Long, StudyGroup> lastEntry = null;
            for (Map.Entry<Long, StudyGroup> entry : ServerCollectionManager.group.entrySet()) {
                lastEntry = entry;
            }

            dataBaseManager.insertIntoCoordinates(user.getUserName(), (float) lastEntry.getValue().getCoordinates().getX(), lastEntry.getValue().getCoordinates().getY());
            dataBaseManager.insertIntoLocation(user.getUserName(), lastEntry.getValue().getGroupAdmin().getLocation().getX(), lastEntry.getValue().getGroupAdmin().getLocation().getY(), lastEntry.getValue().getGroupAdmin().getLocation().getLocationName());
            dataBaseManager.insertIntoPerson(user.getUserName(), lastEntry.getValue().getGroupAdmin().getAdminName(), lastEntry.getValue().getGroupAdmin().getHeight(), lastEntry.getValue().getGroupAdmin().getEyeColor(), lastEntry.getValue().getGroupAdmin().getHairColor(), lastEntry.getValue().getGroupAdmin().getNationality());
            dataBaseManager.insertIntoStudyGroup(lastEntry.getValue().getGroupId(), user.getUserName(), lastEntry.getValue().getGroupName(), lastEntry.getValue().getCreationDate(), lastEntry.getValue().getStudentsCount(), lastEntry.getValue().getExpelledStudents(), lastEntry.getValue().getTransferredStudents(), lastEntry.getValue().getFormOfEducation(), lastEntry.getValue().getGroupAdmin().getAdminName());

            return "{" + lastEntry + "}";
        } else {
            return "You need to reg or log_in";
        }
    }
}
