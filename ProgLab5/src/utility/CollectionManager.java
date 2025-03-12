package utility;

import models.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Менеджер осуществляющий валидацию и инициализацию всех данных для элемента коллекции.
 * @author butareyka
 */
public class CollectionManager implements Validatable, CollectionMethods{
    public static Map<Long, StudyGroup> group = new LinkedHashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    
    StudyGroup studyGroup = new StudyGroup();
    Coordinates coord = new Coordinates();
    Person person = new Person();
    Location locat = new Location();
    
    public boolean validate(String value){
        switch (value){
            case "groupName":
                if ((studyGroup.getGroupName() == null) || (studyGroup.getGroupName().isEmpty())){
                    System.out.println("Поле name не может быть/иметь пустым или значение null!");
                    return true;
                }
                break;
            case "groupCoordinatesX":
                if ((Objects.equals(String.valueOf(coord.getX()), "")) || (coord.getX() <= -339)) {
                    System.out.println("Поле groupCoordinatesX не может иметь значение null!");
                    return true;
                }
                break;
            case "coordinates":
                if (studyGroup.getCoordinates() == null) {
                    System.out.println("Поле coordinates не может иметь значение null!");
                    return true;
                }
                break;
            case "creationDate":
                if (studyGroup.getCreationDate() == null){
                    System.out.println("Поле creationDate не может быть null!");
                    return true;
                }
                break;
            case "studentsCount":
                if (studyGroup.getStudentsCount() <= 0) {
                    System.out.println("Поле studentsCount не может быть отрицательным или равным 0!");
                    return true;
                }
                break;
            case "expelledStudents":
                if (studyGroup.getExpelledStudents() <= 0) {
                    System.out.println("Поле expelledStudents не может быть отрицательным или равным 0!");
                    return true;
                }
                break;
            case "transferredStudents":
                if ((studyGroup.getTransferredStudents() <= 0) || (studyGroup.getTransferredStudents() == null)) {
                    System.out.println("Поле transferredStudents не может быть/иметь отрицательным или равным 0 и значении null!");
                    return true;
                }
                break;
            case "formOfEducation":
                if (studyGroup.getFormOfEducation() == null) {
                    System.out.println("Поле formOfEducation не может быть null!");
                    return true;
                }
                break;
            case "groupAdmin":
                if (studyGroup.getGroupAdmin() == null) {
                    System.out.println("Поле groupAdmin не может быть null!");
                    return true;
                }
                break;
            case "adminName":
                if ((person.getAdminName() == null) || (person.getAdminName().isEmpty())) {
                    System.out.println("Поле adminName не может быть/иметь пустой и значение null!");
                    return true;
                }
                break;
            case "height":
                if (person.getHeight() <= 0) {
                    System.out.println("Поле height не может быть отрицательной и равной нулю!");
                    return true;
                }
                break;
            case "eyeColor":
                if (Objects.equals(String.valueOf(person.getEyeColor()), "")) {
                    System.out.println("Поле eyeColor не может быть null!");
                    return true;
                }
                break;
            case "hairColor":
                if (person.getHairColor() == null) {
                    System.out.println("Поле hairColor не может быть null!");
                    return true;
                }
                break;
            case "nationality":
                if (Objects.equals(String.valueOf(person.getNationality()), "")) {
                    System.out.println("Поле nationality не может быть null!");
                    return true;
                }
                break;
            case "locationCoordinatesY":
                if (locat.getY() == null) {
                    System.out.println("Поле locationCoordinatesY не может быть null!");
                    return true;
                }
                break;
            case "locationName":
                if ((locat.getLocationName() == null) || (locat.getLocationName().length() >= 979)) {
                    System.out.println("Поле locationName не может быть null или больше 979!");
                    return true;
                }
                break;
            case "location":
                if (Objects.equals(String.valueOf(person.getLocation()), "")) {
                    System.out.println("Поле location не может быть null!");
                    return true;
                }
                break;
        }
        return false;
    }
    
    public Long addGroupId(){
        boolean f = true;
        Long group_id = 1L;
        while (f){
            if (group.containsKey(group_id)) {
                group_id += 1;
            } else{
                f = false;
            }
        }
        studyGroup.setGroup_id(group_id);
        return group_id;
    }

    public String addGroupName(){
        System.out.println("Введите название группы");
        System.out.println("Данные принимаются в виде (Имя_группы)");
        String groupName = scanner.nextLine();
        studyGroup.setGroupName(groupName);
        if (validate("groupName")) {
            groupName = addGroupName();
        }
        return groupName;
    }

    public double addGroupCoordinatesX(){
        String groupCoordinatesXString = scanner.next();
        double groupCoordinatesX = 0;
        try {
            groupCoordinatesX = Double.parseDouble(groupCoordinatesXString);
            coord.setX(groupCoordinatesX);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            groupCoordinatesX = addGroupCoordinatesX();
        }

        if (validate("groupCoordinatesX")){
            groupCoordinatesX = addGroupCoordinatesX();
        }
        return groupCoordinatesX;
    }

    public int addGroupCoordinatesY(){
        String groupCoordinatesYString = scanner.next();
        int groupCoordinatesY = 0;
        try {
            groupCoordinatesY = Integer.parseInt(groupCoordinatesYString);
            coord.setY(groupCoordinatesY);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            groupCoordinatesY = addGroupCoordinatesY();
        }
        return groupCoordinatesY;
    }

    public Coordinates addCoordinates(){
        System.out.println("Введите координаты группы: ");
        System.out.println("Данные принимаются в виде (x = 12,0 или y = 12 \"Enter\" 12)");

        Coordinates coordinates = null;
        try{
            coordinates = new Coordinates(addGroupCoordinatesX(), addGroupCoordinatesY());
            studyGroup.setCoordinates(coordinates);
        } catch (Exception e){
            coordinates = addCoordinates();
        }
        if (validate("coordinates")) {
            coordinates = addCoordinates();
        }
        return coordinates;
    }

    public LocalDateTime addCreationDate(){
        LocalDateTime creationDate = LocalDateTime.now();
        studyGroup.setCreationDate(creationDate);
        if (validate("creationDate")) {
            creationDate = addCreationDate();
        }
        return creationDate;
    }

    public int addStudentsCount(){
        System.out.println("Введите число студентов группы");
        System.out.println("Данные принимаются в виде: (12, 32,...)");
        String studentsCountString = scanner.next();
        int studentsCount = 0;
        try{
            studentsCount = Integer.parseInt(studentsCountString);
            studyGroup.setStudentsCount(studentsCount);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            studentsCount = addStudentsCount();
        }
        if (validate("studentsCount")) {
            studentsCount = addStudentsCount();
        }
        return studentsCount;
    }

    public Long addExpelledStudents(){
        System.out.println("Введите число исключенных студентов");
        System.out.println("Данные принимаются в виде (12, 23,...)");
        String expelledStudentsString = scanner.next();
        Long expelledStudents = null;
        try{
            expelledStudents = Long.parseLong(expelledStudentsString);
            studyGroup.setExpelledStudents(expelledStudents);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            expelledStudents = addExpelledStudents();

        }
        if (validate("expelledStudents")) {
            expelledStudents = addExpelledStudents();
        }
        return expelledStudents;
    }

    public Integer addTransferredStudents(){
        System.out.println("Введите число переведенных студентов");
        System.out.println("Данные принимаются в виде (12, 24,...)");
        String transferredStudentsString = scanner.next();
        Integer transferredStudents = null;
        try {
            transferredStudents = Integer.parseInt(transferredStudentsString);
            studyGroup.setTransferredStudents(transferredStudents);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            transferredStudents = addTransferredStudents();
        }
        if (validate("transferredStudents")) {
            transferredStudents = addTransferredStudents();
        }
        return transferredStudents;
    }

    public FormOfEducation addFormOfEducation(){
        System.out.println("Выберете форму обучения студентов из приведенного списка ниже: ");
        FormOfEducation[] formOfEducationsArray = FormOfEducation.values();
        for (FormOfEducation eduFrom: formOfEducationsArray){
            System.out.println(eduFrom);
        }
        System.out.println("Данные принимаются в виде (ZDESb, TAMA,...)");
        FormOfEducation formOfEducation = null;
        try{
            formOfEducation = FormOfEducation.valueOf(scanner.next().toUpperCase());
            studyGroup.setFormOfEducation(formOfEducation);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            formOfEducation = addFormOfEducation();
        }
        if (validate("formOfEducation")){
            formOfEducation = addFormOfEducation();
        }
        return formOfEducation;
    }

    public String addAdminName(){
        System.out.println("Введите имя старосты группы:");
        System.out.println("Данные принимаются в виде (Имя_старосты)");
        String adminName = scanner.next();
        person.setAdminName(adminName);
        if (validate("adminName")){
            adminName = addAdminName();
        }
        return adminName;
    }

    public long addHeight(){
        System.out.println("Введите рост старосты группы:");
        System.out.println("Данные принимаются в виде (160, 170,...)");
        String heightString = scanner.next();
        long height = 0;
        try {
            height = Long.parseLong(heightString);
            person.setHeight(height);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            height = addHeight();
        }
        if (validate("height")){
            height = addHeight();
        }
        return height;
    }

        public EyesColor addEyeColor(){
        System.out.println("Выберете цвет глаз из приведенного списка ниже:");
        EyesColor[] eyesColorsArray = EyesColor.values();
        for (EyesColor eColor: eyesColorsArray){
            System.out.println(eColor);
        }
        System.out.println("Данные принимаются в виде (COLOR, COLOR_COLOR,...)");
        EyesColor eyeColor = null;
        try {
            eyeColor = EyesColor.valueOf(scanner.next().toUpperCase());
            person.setEyeColor(eyeColor);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            eyeColor = addEyeColor();
        }
        if (validate("eyeColor")){
            eyeColor = addEyeColor();
        }
        return eyeColor;
    }

    public HairColor addHairColor(){
        System.out.println("Выберете цвет волос из приведенного списка ниже:");
        HairColor[] hairColorsArray = HairColor.values();
        for (HairColor hColor: hairColorsArray){
            System.out.println(hColor);
        }
        System.out.println("Данные принимаются в виде (COLOR, COLOR_COLOR,...)");
        HairColor hairColor = null;
        try {
            hairColor = HairColor.valueOf(scanner.next().toUpperCase());
            person.setHairColor(hairColor);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            hairColor = addHairColor();
        }
        if (validate("hairColor")){
            hairColor = addHairColor();
        }
        return hairColor;
    }

        public Country addNationality(){
        System.out.println("Выберете цвет национальность из приведенного списка ниже:");
        Country[] nationalitiesArray = Country.values();
        for (Country typeNationality: nationalitiesArray){
            System.out.println(typeNationality);
        }
        System.out.println("Данные принимаются в виде (RUSKIY, *IGGER,...)");
        Country nationality = null;
        try {
            nationality = Country.valueOf(scanner.next().toUpperCase());
            person.setNationality(nationality);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            nationality = addNationality();
        }
        if (validate("nationality")){
            nationality = addNationality();
        }
        return nationality;
    }

    public long addLocationCoordinatesX(){
        String locationCoordinatesXString = scanner.next();
        long locationCoordinatesX = 0;
        try {
            locationCoordinatesX = Long.parseLong(locationCoordinatesXString);
            locat.setX(locationCoordinatesX);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            locationCoordinatesX = addLocationCoordinatesX();
        }
        return locationCoordinatesX;
    }

    public Double addLocationCoordinatesY(){
        String locationCoordinatesYString = scanner.next();
        Double locationCoordinatesY = null;
        try {
            locationCoordinatesY = Double.parseDouble(locationCoordinatesYString);
            locat.setY(locationCoordinatesY);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            locationCoordinatesY = addLocationCoordinatesY();
        }
        if (validate("locationCoordinatesY")){
            locationCoordinatesY = addLocationCoordinatesY();
        }
        return locationCoordinatesY;
    }

    public String addLocationName(){
        String locationName = scanner.next();
        locat.setLocationName(locationName);
        if (validate("locationName")){
            locationName = addLocationName();
        }
        return locationName;
    }

    public Location addLocation(){
        System.out.println("Введите название места и его местоположение: ");
        System.out.println("Данные принимаются в виде (Название_местоположения \"Enter\" y = 12,0/12 \"Enter\" x = 12)");
        Location location = null;
        try {
            location = new Location(addLocationName(), addLocationCoordinatesX(), addLocationCoordinatesY());
            person.setLocation(location);
        } catch (Exception e){
            return addLocation();
        }
        if (validate("location")){
            location = addLocation();
        }
        return location;
    }

    public Person addGroupAdmin(){
        System.out.println("Заполнение данных старосты группы");
        Person groupAdmin = new Person(addAdminName(), addHeight(), addEyeColor(), addHairColor(), addNationality(), addLocation());
        studyGroup.setGroupAdmin(groupAdmin);
        if (validate("groupAdmin")){
            groupAdmin = addGroupAdmin();
        }
        return groupAdmin;
    }
}