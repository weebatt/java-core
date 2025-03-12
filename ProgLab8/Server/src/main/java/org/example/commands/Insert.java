package org.example.commands;

import org.example.daba.DataBaseManager;
import org.example.models.*;
import org.example.utility.ServerCollectionManager;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

public class Insert extends ServerCommand implements Serializable {
    public Insert(){
        super("insert", "добавить новый элемент с заданным ключом.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws SQLException, IOException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.checkUser(user.getUserName())) {
            ServerCollectionManager collectionManager = new ServerCollectionManager();
            StudyGroup studyGroup = (StudyGroup) value;
            collectionManager.addGroupId();
            studyGroup.setGroupId(collectionManager.takeGroupId());
            studyGroup.setUserName(user.getUserName());
            ServerCollectionManager.group.put(collectionManager.takeGroupId(), studyGroup);

            Map.Entry<Long, StudyGroup> lastEntry = null;
            for (Map.Entry<Long, StudyGroup> entry : ServerCollectionManager.group.entrySet()) {
                lastEntry = entry;
            }

            dataBaseManager.insertIntoCoordinates(user.getUserName(), (float) lastEntry.getValue().getCoordinates().getX(), lastEntry.getValue().getCoordinates().getY());
            dataBaseManager.insertIntoLocation(user.getUserName(), lastEntry.getValue().getGroupAdmin().getLocation().getX(), lastEntry.getValue().getGroupAdmin().getLocation().getY(), lastEntry.getValue().getGroupAdmin().getLocation().getLocationName());
            dataBaseManager.insertIntoPerson(user.getUserName(), lastEntry.getValue().getGroupAdmin().getAdminName(), lastEntry.getValue().getGroupAdmin().getHeight(), lastEntry.getValue().getGroupAdmin().getEyeColor(), lastEntry.getValue().getGroupAdmin().getHairColor(), lastEntry.getValue().getGroupAdmin().getNationality());
            dataBaseManager.insertIntoStudyGroup(user.getUserName(), lastEntry.getValue().getGroupName(), lastEntry.getValue().getCreationDate(), lastEntry.getValue().getStudentsCount(), lastEntry.getValue().getExpelledStudents(), lastEntry.getValue().getTransferredStudents(), lastEntry.getValue().getFormOfEducation());
            return "1";
        } else {
            return "0";
        }
    }
}
