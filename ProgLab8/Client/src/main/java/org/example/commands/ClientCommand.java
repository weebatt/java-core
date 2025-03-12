package org.example.commands;

import org.example.models.*;

import java.io.Serializable;

public abstract class ClientCommand implements Serializable {

    public Object executionForRequestReturn(Object object){
        return null;
    }
    public Object executionForInsertObject(String groupName, float coordinatesX, int coordinatesY, int studentsCount,
                                           Long expelledStudents, Integer transferredStudents, String adminName,
                                           Long height, int locationX, float locationY, String locationName, FormOfEducation formOfEducation,
                                           EyesColor eyesColor, HairColor hairColor, Country nationality, String username){
        return null;
    }
    public void executionForRequestVoid(Object object){}
}
