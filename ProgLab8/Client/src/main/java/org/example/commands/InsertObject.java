package org.example.commands;

import org.example.models.*;

import java.io.Serializable;
import java.time.LocalDateTime;

public class InsertObject extends ClientCommand implements Serializable{
    @Override
        public Object executionForInsertObject(String groupName, float coordinatesX, int coordinatesY, int studentsCount,
                                           Long expelledStudents, Integer transferredStudents, String adminName,
                                           Long height, int locationX, float locationY, String locationName, FormOfEducation formOfEducation,
                                           EyesColor eyesColor, HairColor hairColor, Country nationality, String username){
        Coordinates coordinates = new Coordinates();
        coordinates.setX(coordinatesX);
        coordinates.setY(coordinatesY);
        Location location = new Location();
        location.setX(locationX);
        location.setY(locationY);
        location.setLocationName(locationName);
        Person person = new Person();
        person.setAdminName(adminName);
        person.setHeight(height);
        person.setEyeColor(eyesColor);
        person.setHairColor(hairColor);
        person.setNationality(nationality);
        person.setLocation(location);
        StudyGroup studyGroup = new StudyGroup(groupName, LocalDateTime.now(), coordinates, studentsCount, expelledStudents, transferredStudents, formOfEducation, username, person);
        return studyGroup;
    }
}
