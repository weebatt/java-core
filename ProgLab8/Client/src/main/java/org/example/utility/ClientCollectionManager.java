package org.example.utility;

import org.example.models.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ClientCollectionManager implements Serializable {
    private final Scanner scanner = new Scanner(System.in);

    StudyGroup studyGroup = new StudyGroup();
    Coordinates coord = new Coordinates();
    Person person = new Person();
    Location locat = new Location();
    Validator validator = new Validator();

    private String groupName;
    private double groupCoordinatesX;
    private int groupCoordinatesY;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private int studentsCount;
    private Long expelledStudents;
    private Integer transferredStudents;
    private FormOfEducation formOfEducation;
    private String userName;
    private String adminName;
    private long height;
    private EyesColor eyeColor;
    private HairColor hairColor;
    private Country nationality;
    private long locationCoordinatesX;
    private Float locationCoordinatesY;
    private String locationName;
    private Location location;
    private Person groupAdmin;

    public void addGroupName(){
        System.out.println("Введите название группы");
        System.out.println("Данные принимаются в виде (Имя_группы)");
        groupName = scanner.next();
        studyGroup.setGroupName(groupName);
    }

    public String takeGroupName(){
        if (validator.validate("groupName", groupName)){
            addGroupName();
        }
        return groupName;
    }

    public void addGroupCoordinatesX(){
        String groupCoordinatesXString = scanner.next();
        groupCoordinatesX = 0;
        try {
            groupCoordinatesX = Double.parseDouble(groupCoordinatesXString);
            coord.setX(groupCoordinatesX);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            addGroupCoordinatesX();
        }
    }

    public double takeGroupCoordinatesX(){
        if (validator.validate("groupCoordinatesX", groupCoordinatesX)){
            addGroupCoordinatesX();
        }
        return groupCoordinatesX;
    }

    public void addGroupCoordinatesY(){
        String groupCoordinatesYString = scanner.next();
        groupCoordinatesY = 0;
        try {
            groupCoordinatesY = Integer.parseInt(groupCoordinatesYString);
            coord.setY(groupCoordinatesY);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            addGroupCoordinatesY();
        }
    }

    public int takeGroupCoordinatesY(){
        return groupCoordinatesY;
    }

    public void addCoordinates(){
        System.out.println("Введите координаты группы: ");
        System.out.println("Данные принимаются в виде (x = 12,0 или y = 12 \"Enter\" 12)");

        coordinates = null;
        addGroupCoordinatesX();
        addGroupCoordinatesY();
        try{
            coordinates = new Coordinates(takeGroupCoordinatesX(), takeGroupCoordinatesY(), takeUserName());
            studyGroup.setCoordinates(coordinates);
        } catch (Exception e){
            System.out.println("Ошибка при заполнении конструктора для Coordinates! " + e.getMessage());
            addCoordinates();
        }
    }

    public Coordinates takeCoordinates(){
        if (validator.validate("coordinates", coordinates)){
            addCoordinates();
        }
        return coordinates;
    }

    public void addCreationDate(){
        creationDate = LocalDateTime.now();
        studyGroup.setCreationDate(creationDate);
    }

    public LocalDateTime takeCreationDate(){
        if (validator.validate("creationDate", creationDate)){
            addCreationDate();
        }
        return creationDate;
    }

    public void addStudentsCount(){
        System.out.println("Введите число студентов группы");
        System.out.println("Данные принимаются в виде: (12, 32,...)");
        studentsCount = 0;
        String studentsCountString = scanner.next();
        try{
            studentsCount = Integer.parseInt(studentsCountString);
            studyGroup.setStudentsCount(studentsCount);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            addStudentsCount();
        }
    }

    public int takeStudentsCount(){
        if (validator.validate("studentsCount", studentsCount)){
            addStudentsCount();
        }
        return studentsCount;
    }

    public void addExpelledStudents(){
        System.out.println("Введите число исключенных студентов");
        System.out.println("Данные принимаются в виде (12, 23,...)");
        expelledStudents = null;
        String expelledStudentsString = scanner.next();
        try{
            expelledStudents = Long.parseLong(expelledStudentsString);
            studyGroup.setExpelledStudents(expelledStudents);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            addExpelledStudents();

        }
    }

    public Long takeExpelledStudents(){
        if (validator.validate("expelledStudents", expelledStudents)){
            addExpelledStudents();
        }
        return expelledStudents;
    }

    public void addTransferredStudents(){
        System.out.println("Введите число переведенных студентов");
        System.out.println("Данные принимаются в виде (12, 24,...)");
        String transferredStudentsString = scanner.next();
        transferredStudents = null;
        try {
            transferredStudents = Integer.parseInt(transferredStudentsString);
            studyGroup.setTransferredStudents(transferredStudents);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            addTransferredStudents();
        }
    }

    public Integer takeTransferredStudents(){
        if (validator.validate("transferredStudents", transferredStudents)){
            addTransferredStudents();
        }
        return transferredStudents;
    }

    public void addFormOfEducation(){
        System.out.println("Выберете форму обучения студентов из приведенного списка ниже: ");
        FormOfEducation[] formOfEducationsArray = FormOfEducation.values();
        for (FormOfEducation eduFrom: formOfEducationsArray){
            System.out.println(eduFrom);
        }
        System.out.println("Данные принимаются в виде (ZDESb, TAMA,...)");
        formOfEducation = null;
        try{
            formOfEducation = FormOfEducation.valueOf(scanner.next().toUpperCase());
            studyGroup.setFormOfEducation(formOfEducation);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            addFormOfEducation();
        }
    }

    public FormOfEducation takeFormOfEducation(){
        if (validator.validate("formOfEducation", formOfEducation)){
            addFormOfEducation();
        }
        return formOfEducation;
    }

    public String takeUserName(){
        return userName;
    }

    public void addAdminName(){
        System.out.println("Введите имя старосты группы:");
        System.out.println("Данные принимаются в виде (Имя_старосты)");
        adminName = scanner.next();
        person.setAdminName(adminName);
    }

    public String takeAdminName(){
        if (validator.validate("adminName", adminName)){
            addAdminName();
        }
        return adminName;
    }

    public void addHeight(){
        System.out.println("Введите рост старосты группы:");
        System.out.println("Данные принимаются в виде (160, 170,...)");
        String heightString = scanner.next();
        height = 0;
        try {
            height = Long.parseLong(heightString);
            person.setHeight(height);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            addHeight();
        }
    }

    public long takeHeight(){
        if (validator.validate("height", height)){
            addHeight();
        }
        return height;
    }

    public void addEyeColor(){
        System.out.println("Выберете цвет глаз из приведенного списка ниже:");
        EyesColor[] eyesColorsArray = EyesColor.values();
        for (EyesColor eColor: eyesColorsArray){
            System.out.println(eColor);
        }
        System.out.println("Данные принимаются в виде (COLOR, COLOR_COLOR,...)");
        eyeColor = null;
        try {
            eyeColor = EyesColor.valueOf(scanner.next().toUpperCase());
            person.setEyeColor(eyeColor);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            addEyeColor();
        }
    }

    public EyesColor takeEyeColor(){
        if (validator.validate("eyeColor", eyeColor)){
            addEyeColor();
        }
        return eyeColor;
    }

    public void addHairColor(){
        System.out.println("Выберете цвет волос из приведенного списка ниже:");
        HairColor[] hairColorsArray = HairColor.values();
        for (HairColor hColor: hairColorsArray){
            System.out.println(hColor);
        }
        System.out.println("Данные принимаются в виде (COLOR, COLOR_COLOR,...)");
        hairColor = null;
        try {
            hairColor = HairColor.valueOf(scanner.next().toUpperCase());
            person.setHairColor(hairColor);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            addHairColor();
        }
    }

    public HairColor takeHairColor(){
        if (validator.validate("hairColor", hairColor)){
            addHairColor();
        }
        return hairColor;
    }

    public void addNationality(){
        System.out.println("Выберете цвет национальность из приведенного списка ниже:");
        Country[] nationalitiesArray = Country.values();
        for (Country typeNationality: nationalitiesArray){
            System.out.println(typeNationality);
        }
        System.out.println("Данные принимаются в виде (RUSKIY, *IGGER,...)");
        nationality = null;
        try {
            nationality = Country.valueOf(scanner.next().toUpperCase());
            person.setNationality(nationality);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            addNationality();
        }
    }

    public Country takeNationality(){
        if (validator.validate("nationality", nationality)){
            addNationality();
        }
        return nationality;
    }

    public void addLocationCoordinatesX(){
        String locationCoordinatesXString = scanner.next();
        locationCoordinatesX = 0;
        try {
            locationCoordinatesX = Long.parseLong(locationCoordinatesXString);
            locat.setX(locationCoordinatesX);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            addLocationCoordinatesX();
        }
    }

    public long takeLocationCoordinatesX(){
        return locationCoordinatesX;
    }

    public void addLocationCoordinatesY(){
        String locationCoordinatesYString = scanner.next();
        locationCoordinatesY = null;
        try {
            locationCoordinatesY = Float.parseFloat(locationCoordinatesYString);
            locat.setY(locationCoordinatesY);
        } catch (Exception e){
            System.out.println("Неправильно попробуй еще раз! " + e.getMessage());
            addLocationCoordinatesY();
        }
    }

    public Float takeLocationCoordinatesY(){
        if (validator.validate("locationCoordinatesY", locationCoordinatesY)){
            addLocationCoordinatesY();
        }
        return locationCoordinatesY;
    }

    public void addLocationName(){
        locationName = scanner.next();
        locat.setLocationName(locationName);
    }

    public String takeLocationName(){
        if (validator.validate("locationName", locationName)){
            addLocationName();
        }
        return locationName;
    }

    public void addLocation(){
        System.out.println("Введите название места и его местоположение: ");
        System.out.println("Данные принимаются в виде (Название_местоположения \"Enter\" y = 12,0/12 \"Enter\" x = 12)");
        addLocationCoordinatesX();
        addLocationCoordinatesY();
        addLocationName();
        try {
            location = new Location(takeLocationName(), takeLocationCoordinatesX(), takeLocationCoordinatesY(), takeUserName());
            person.setLocation(location);
        } catch (Exception e){
            System.out.println("Ошибка при заполнении конструктора для Location! " + e.getMessage());
            addLocation();
        }
    }

    public Location takeLocation(){
        if (validator.validate("location", location)){
            addLocation();
        }
        return location;
    }

    public void addGroupAdmin(){
        System.out.println("Заполнение данных старосты группы");
        addAdminName();
        addHeight();
        addEyeColor();
        addHairColor();
        addNationality();
        addLocation();
        try{
            groupAdmin = new Person(takeAdminName(), takeHeight(), takeEyeColor(), takeHairColor(), takeNationality(), takeLocation(), takeUserName());
            studyGroup.setGroupAdmin(groupAdmin);
        } catch (Exception e){
            System.out.println("Ошибка при заполнении конструктора для GroupAdmin! " + e.getMessage());
            addGroupAdmin();
        }
    }

    public Person takeGroupAdmin(){
        return groupAdmin;
    }
}