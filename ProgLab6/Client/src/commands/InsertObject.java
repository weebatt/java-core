package commands;

import models.StudyGroup;
import org.apache.commons.lang3.SerializationUtils;
import utility.ClientCollectionManager;

import java.io.Serializable;

public class InsertObject extends ClientCommand implements Serializable{
    @Override
    public Object executionForRequestReturn(Object object){
        ClientCollectionManager collectionManager = new ClientCollectionManager();
        collectionManager.addGroupName();
        collectionManager.addCreationDate();
        collectionManager.addCoordinates();
        collectionManager.addStudentsCount();
        collectionManager.addExpelledStudents();
        collectionManager.addTransferredStudents();
        collectionManager.addFormOfEducation();
        collectionManager.addGroupAdmin();
        return SerializationUtils.serialize(new StudyGroup(collectionManager.takeGroupName(), collectionManager.takeCreationDate(), collectionManager.takeCoordinates(), collectionManager.takeStudentsCount(), collectionManager.takeExpelledStudents(), collectionManager.takeTransferredStudents(), collectionManager.takeFormOfEducation(), collectionManager.takeGroupAdmin()));
    }
}
