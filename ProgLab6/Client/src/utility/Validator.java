package utility;

import java.io.Serializable;

public class Validator implements ValidatorMethods, Serializable {
    public boolean validate(String value, Object object){
        switch (value){
            case "groupName":
                String objectStr1 = (String) object;
                if ((object == null) || (objectStr1.isEmpty())){
                    System.out.println("Поле name не может быть/иметь пустым или значение null!");
                    return true;
                }
                break;
            case "groupCoordinatesX":
                double objectDouble1 = (double) object;
                if ((object == "") || (objectDouble1 <= -339)) {
                    System.out.println("Поле groupCoordinatesX не может иметь значение null!");
                    return true;
                }
                break;
            case "coordinates":
                if (object == null) {
                    System.out.println("Поле coordinates не может иметь значение null!");
                    return true;
                }
                break;
            case "creationDate":
                if (object == null){
                    System.out.println("Поле creationDate не может быть null!");
                    return true;
                }
                break;
            case "studentsCount":
                int objectInt = (int) object;
                if (objectInt <= 0) {
                    System.out.println("Поле studentsCount не может быть отрицательным или равным 0!");
                    return true;
                }
                break;
            case "expelledStudents":
                Long objectLong = (Long) object;
                if (objectLong <= 0) {
                    System.out.println("Поле expelledStudents не может быть отрицательным или равным 0!");
                    return true;
                }
                break;
            case "transferredStudents":
                Integer objectInteger = (Integer) object;
                if ((object == null) || (objectInteger <= 0)) {
                    System.out.println("Поле transferredStudents не может быть/иметь отрицательным или равным 0 и значении null!");
                    return true;
                }
                break;
            case "formOfEducation":
                if (object == null) {
                    System.out.println("Поле formOfEducation не может быть null!");
                    return true;
                }
                break;
            case "groupAdmin":
                if (object == null) {
                    System.out.println("Поле groupAdmin не может быть null!");
                    return true;
                }
                break;
            case "adminName":
                String objectStr2 = (String) object;
                if ((object == null) || (objectStr2.isEmpty())) {
                    System.out.println("Поле adminName не может быть/иметь пустой и значение null!");
                    return true;
                }
                break;
            case "height":
                long objectPrimalLong = (long) object;
                if (objectPrimalLong <= 0) {
                    System.out.println("Поле height не может быть отрицательной и равной нулю!");
                    return true;
                }
                break;
            case "eyeColor":
                if (object == null) {
                    System.out.println("Поле eyeColor не может быть null!");
                    return true;
                }
                break;
            case "hairColor":
                if (object == null) {
                    System.out.println("Поле hairColor не может быть null!");
                    return true;
                }
                break;
            case "nationality":
                if (object == null) {
                    System.out.println("Поле nationality не может быть null!");
                    return true;
                }
                break;
            case "locationCoordinatesY":
                Double objectDouble2 = (Double) object;
                if (objectDouble2 == null) {
                    System.out.println("Поле locationCoordinatesY не может быть null!");
                    return true;
                }
                break;
            case "locationName":
                String objectStr3 = (String) object;
                if ((objectStr3 == null) || (objectStr3.length() >= 979)) {
                    System.out.println("Поле locationName не может быть null или больше 979!");
                    return true;
                }
                break;
            case "location":
                if (object == null) {
                    System.out.println("Поле location не может быть null!");
                    return true;
                }
                break;
        }
        return false;
    }
}
