package org.example.commands;

import org.example.daba.DataBaseManager;
import org.example.models.StudyGroup;
import org.example.models.User;
import org.example.utility.ServerCollectionManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;


public class Save extends ServerCommand implements Serializable {
    public Save(){
        super("save", "сохранить коллекцию в файл.");
    }

    @Override
    public Object executionForResponse(Object str, User user) throws IOException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.checkUser(user.getUserName())) {
            try {
                for (Map.Entry<Long, StudyGroup> item : ServerCollectionManager.group.entrySet()) {
                    dataBaseManager.deleteAllCollectionTable(user.getUserName());
                    dataBaseManager.insertIntoCoordinates(user.getUserName(), (float) item.getValue().getCoordinates().getX(), item.getValue().getCoordinates().getY());
                    dataBaseManager.insertIntoLocation(user.getUserName(), item.getValue().getGroupAdmin().getLocation().getX(), item.getValue().getGroupAdmin().getLocation().getY(), item.getValue().getGroupAdmin().getLocation().getLocationName());
                    dataBaseManager.insertIntoPerson(user.getUserName(), item.getValue().getGroupAdmin().getAdminName(), item.getValue().getGroupAdmin().getHeight(), item.getValue().getGroupAdmin().getEyeColor(), item.getValue().getGroupAdmin().getHairColor(), item.getValue().getGroupAdmin().getNationality());
                    dataBaseManager.insertIntoStudyGroup(user.getUserName(), item.getValue().getGroupName(), item.getValue().getCreationDate(), item.getValue().getStudentsCount(), item.getValue().getExpelledStudents(), item.getValue().getTransferredStudents(), item.getValue().getFormOfEducation());
                }
                return "Data saved successfully";
            } catch (Exception e) {
                return "Error in processing saving data into database";
            }
        } else {
            return "You need to reg or log_in";
        }
    }
}
