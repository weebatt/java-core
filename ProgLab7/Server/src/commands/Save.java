package commands;

import daba.DataBaseManager;
import models.Person;
import models.StudyGroup;
import models.User;
import utility.ServerCollectionManager;

import java.io.Serializable;
import java.util.Map;


public class Save extends ServerCommand implements Serializable {
    public Save(){
        super("save", "сохранить коллекцию в файл.");
    }

    @Override
    public Object executionForResponse(Object str, User user){
        if (Authorization.list.contains(user.getUserName())) {
            try {
                DataBaseManager dataBaseManager = new DataBaseManager();
                for (Map.Entry<Long, StudyGroup> item : ServerCollectionManager.group.entrySet()) {
                    dataBaseManager.deleteAllCollectionTable(user.getUserName());
                    dataBaseManager.insertIntoCoordinates(user.getUserName(), (float) item.getValue().getCoordinates().getX(), item.getValue().getCoordinates().getY());
                    dataBaseManager.insertIntoLocation(user.getUserName(), item.getValue().getGroupAdmin().getLocation().getX(), item.getValue().getGroupAdmin().getLocation().getY(), item.getValue().getGroupAdmin().getLocation().getLocationName());
                    dataBaseManager.insertIntoPerson(user.getUserName(), item.getValue().getGroupAdmin().getAdminName(), item.getValue().getGroupAdmin().getHeight(), item.getValue().getGroupAdmin().getEyeColor(), item.getValue().getGroupAdmin().getHairColor(), item.getValue().getGroupAdmin().getNationality());
                    dataBaseManager.insertIntoStudyGroup(item.getValue().getGroupId(), user.getUserName(), item.getValue().getGroupName(), item.getValue().getCreationDate(), item.getValue().getStudentsCount(), item.getValue().getExpelledStudents(), item.getValue().getTransferredStudents(), item.getValue().getFormOfEducation(), item.getValue().getGroupAdmin().getAdminName());
                }
                return "Data saved successfully";
            } catch (Exception e) {
                return "Error while passing data parseCollectionToCSV";
            }
        } else {
            return "You need to reg or log_in";
        }
    }
}
