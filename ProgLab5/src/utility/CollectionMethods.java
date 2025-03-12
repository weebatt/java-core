package utility;

import models.*;

import java.time.LocalDateTime;

/**
 * Интерфейс хранящий все методы CollectionManager.
 * @author butareyka
 */
public interface CollectionMethods {
    Number addGroupId();
    LocalDateTime addCreationDate();
    String addGroupName();
    double addGroupCoordinatesX();
    int addGroupCoordinatesY();
    Coordinates addCoordinates();
    int addStudentsCount();
    Long addExpelledStudents();
    Integer addTransferredStudents();
    FormOfEducation addFormOfEducation();
    Person addGroupAdmin();
    String addAdminName();
    long addHeight();
    EyesColor addEyeColor();
    HairColor addHairColor();
    Country addNationality();
    long addLocationCoordinatesX();
    Double addLocationCoordinatesY();
    String addLocationName();
    Location addLocation();
}
