package org.example.daba;

import org.example.models.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Properties;

public class DataBaseManager {
    private Connection connection;
    private String url = System.getenv("URL_DB");
    private Properties properties = new Properties();

    public DataBaseManager(){
    }

    public void connectDataBase(String url, Properties properties){
        try {
            connection = DriverManager.getConnection(url, properties);
            System.err.println("Successfully connected to the database\n");
        } catch (SQLException e) {
            System.err.println("Error with connection to database " + e.getMessage());
        }
    }

    public void initDataBase(){
        createTableUser();
        createTableStudyGroup();
    }

    public void createSeqId(){
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "CREATE SEQUENCE IF NOT EXISTS ID_SEQ START WITH 1 INCREMENT BY 1;";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error sending request drop ID_SEQ " + e.getMessage());
        }
    }
//    public void dropEnumHairColor(){
//        try{
//            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            String sql = "DROP TYPE HairColor";
//            statement.executeUpdate(sql);
//            statement.close();
//        } catch (SQLException e){
//            System.out.println("Error sending request drop HairColor " + e.getMessage());
//        }
//    }
//
//    public void dropEnumFormOfEducation(){
//        try{
//            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            String sql = "DROP TYPE FormOfEducation;";
//            statement.executeUpdate(sql);
//            statement.close();
//        } catch (SQLException e){
//            System.out.println("Error sending request drop FormOfEducation " + e.getMessage());
//        }
//    }
//
//    public void dropEnumEyesColor(){
//        try{
//            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            String sql = "DROP TYPE EyesColor;";
//            statement.executeUpdate(sql);
//            statement.close();
//        } catch (SQLException e){
//            System.out.println("Error sending request drop EyesColor " + e.getMessage());
//        }
//    }
//
//    public void dropEnumCountry(){
//        try{
//            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            String sql = "DROP TYPE Country;";
//            statement.executeUpdate(sql);
//            statement.close();
//        } catch (SQLException e){
//            System.out.println("Error sending request drop Country " + e.getMessage());
//        }
//    }
//    public void dropSeq(){
//        try{
//            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            String sql = "DROP SEQUENCE IF EXISTS ID_SEQ;";
//            statement.executeUpdate(sql);
//            statement.close();
//        } catch (SQLException e){
//            System.out.println("Error sending request drop Seq " + e.getMessage());
//        }
//    }

    public void createEnumHairColor(){
        try{
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "CREATE TYPE HairColor AS ENUM ('RED', 'BLACK', 'BLUE', 'BROWN');";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e){
            System.err.println("Error sending request HairColor " + e.getMessage());
        }
    }

    public void createEnumEyesColor(){
        try{
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "CREATE TYPE EyesColor AS ENUM ('RED', 'BLACK', 'BLUE', 'WHITE');";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e){
            System.err.println("Error sending request EyesColor " + e.getMessage());
        }
    }

    public void createEnumFormOfEducation(){
        try{
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "CREATE TYPE FormOfEducation AS ENUM ('DISTANCE_EDUCATION', 'FULL_TIME_EDUCATION', 'EVENING_CLASSES');";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e){
            System.err.println("Error sending request FormOfEducation " + e.getMessage());
        }
    }

    public void createEnumCountry(){
        try{
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "CREATE TYPE Country AS ENUM ('GERMANY', 'SPAIN', 'INDIA', 'THAILAND');";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e){
            System.err.println("Error sending request Country " + e.getMessage());
        }
    }

    public void createTableCoordinates(){
        try{
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "CREATE TABLE IF NOT EXISTS Coordinates (" +
                    "    coordinatesId SERIAL PRIMARY KEY," +
                    "    userName TEXT," +
                    "    coordinatesX FLOAT," +
                    "    coordinatesY INT" +
                    ");";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e){
            System.err.println("Error sending request Coordinates " + e.getMessage());
        }
    }

    public void createTableLocation(){
        try{
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "CREATE TABLE IF NOT EXISTS Location (" +
                    "    locationId SERIAL PRIMARY KEY," +
                    "    userName TEXT," +
                    "    locationX BIGINT," +
                    "    locationY FLOAT," +
                    "    locationName TEXT" +
                    ");";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e){
            System.err.println("Error sending request Location " + e.getMessage());
        }
    }

    public void createTablePerson(){
//        createEnumCountry();
//        createEnumHairColor();
//        createEnumEyesColor();
        createTableLocation();
        try{
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "CREATE TABLE IF NOT EXISTS Person (" +
                    "    personId SERIAL PRIMARY KEY," +
                    "    adminName TEXT," +
                    "    userName TEXT," +
                    "    height BIGINT," +
                    "    eyeColor EyesColor," +
                    "    hairColor HairColor," +
                    "    nationality Country," +
                    "    locationId SERIAL," +
                    "    FOREIGN KEY (locationId) REFERENCES Location(locationId)" +
                    ");";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error sending request Person " + e.getMessage());
        }
    }

    public void createTableStudyGroup(){
//        createEnumFormOfEducation();
        createTableCoordinates();
        createTablePerson();
        createSeqId();
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "CREATE TABLE IF NOT EXISTS StudyGroup (" +
                    "    groupId BIGINT PRIMARY KEY DEFAULT nextval('ID_SEQ')," +
                    "    userName TEXT," +
                    "    groupName TEXT," +
                    "    coordinatesId SERIAL," +
                    "    creationDate TIMESTAMP," +
                    "    studentsCount INT," +
                    "    expelledStudents BIGINT," +
                    "    transferredStudents INT," +
                    "    formOfEducation FormOfEducation," +
                    "    personId SERIAL," +
                    "    FOREIGN KEY (coordinatesId) REFERENCES Coordinates(coordinatesId)," +
                    "    FOREIGN KEY (personId) REFERENCES Person(personId)," +
                    "    FOREIGN KEY (userName) REFERENCES Users(userName)" +
                    ");";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error sending request StudyGroup " + e.getMessage());
        }
    }
    public void createTableUser(){
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "CREATE TABLE IF NOT EXISTS Users " +
                    "(userName TEXT PRIMARY KEY, " +
                    " password TEXT);";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error sending request Users " + e.getMessage());
        }
    }

    public boolean checkUser(String userName) throws IOException {
        properties.load(new FileInputStream(System.getenv("PROP")));
        connectDataBase(url, properties);
        boolean exists = false;
        String sql = "SELECT COUNT(*) AS count FROM users WHERE userName = ?";
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                if (count > 0) {
                    exists = true;
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error with checkUser " + e.getMessage());
        }
        return exists;
    }

    public boolean checkPassword(String userName, String passwd) throws IOException {
        properties.load(new FileInputStream(System.getenv("PROP")));
        connectDataBase(url, properties);
        String sql = "SELECT password FROM Users WHERE userName = ?";
        try (PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            prepareStatement.setString(1, userName);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                String hashedInputPassword = PasswordHasher.hashingPassword(passwd);
                prepareStatement.close();
                resultSet.close();
                return hashedInputPassword.equals(hashedPassword);
            }
            prepareStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error with checkPassword " + e.getMessage());
        }
        return false;
    }

    public void registerUser(String userName, String passwd) throws IOException {
        properties.load(new FileInputStream(System.getenv("PROP")));
        connectDataBase(url, properties);
        String sql = "INSERT INTO users (userName, password) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, PasswordHasher.hashingPassword(passwd));
            preparedStatement.executeUpdate();
            System.err.println("User added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding user registration " + e.getMessage());
        }
    }

    public LinkedHashMap<Long, StudyGroup> readFromDataBase() throws IOException {
        properties.load(new FileInputStream(System.getenv("PROP")));
        connectDataBase(url, properties);
        LinkedHashMap<Long, StudyGroup> studyGroupLinkedHashMap = new LinkedHashMap<>();
        String sql = "SELECT * FROM StudyGroup JOIN Coordinates ON StudyGroup.coordinatesId = Coordinates.coordinatesId JOIN Person ON StudyGroup.personId = Person.personId JOIN Location ON Person.locationId = Location.locationId";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                StudyGroup studyGroup = new StudyGroup();
                Coordinates coordinates = new Coordinates();
                Location location = new Location();
                Person person = new Person();
                studyGroup.setGroupId(rs.getLong("groupId"));
                studyGroup.setUserName(rs.getString("userName"));
                studyGroup.setGroupName(rs.getString("groupName"));
                studyGroup.setCreationDate(rs.getDate("creationDate").toLocalDate().atStartOfDay());
                coordinates.setUserName(rs.getString("userName"));
                coordinates.setX(rs.getFloat("coordinatesX"));
                coordinates.setY(rs.getInt("coordinatesY"));
                studyGroup.setCoordinates(coordinates);
                studyGroup.setStudentsCount(rs.getInt("studentsCount"));
                studyGroup.setExpelledStudents(rs.getLong("expelledStudents"));
                studyGroup.setTransferredStudents(rs.getInt("transferredStudents"));
                studyGroup.setFormOfEducation(FormOfEducation.valueOf(rs.getString("formOfEducation")));
                person.setAdminName(rs.getString("adminName"));
                person.setUserName(rs.getString("userName"));
                person.setHeight(rs.getLong("height"));
                person.setEyeColor(EyesColor.valueOf(rs.getString("eyeColor")));
                person.setHairColor(HairColor.valueOf(rs.getString("hairColor")));
                person.setNationality(Country.valueOf(rs.getString("nationality")));
                location.setUserName(rs.getString("userName"));
                location.setLocationName(rs.getString("locationName"));
                location.setX(rs.getInt("locationX"));
                location.setY(rs.getFloat("locationY"));
                person.setLocation(location);
                studyGroup.setGroupAdmin(person);
                studyGroupLinkedHashMap.put(rs.getLong("groupId"), studyGroup);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error reading collection " + e.getMessage());
        }
        return studyGroupLinkedHashMap;
    }

    public void insertIntoCoordinates(String userName, float coordinatesX, int coordinatesY) {
        try {
            properties.load(new FileInputStream(System.getenv("PROP")));
            connectDataBase(url, properties);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Coordinates (userName, coordinatesX, coordinatesY) " +
                            "VALUES (?, ?, ?)"
            );
            preparedStatement.setString(1, userName);
            preparedStatement.setFloat(2, coordinatesX);
            preparedStatement.setInt(3, coordinatesY);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.err.println("Data inserted successfully into Coordinates table\n");
        } catch (SQLException | IOException e) {
            System.err.println("Error inserting data into Coordinates table " + e.getMessage());
        }
    }

    public void insertIntoLocation(String userName, long locationX, float locationY, String locationName) {
        try {
            properties.load(new FileInputStream(System.getenv("PROP")));
            connectDataBase(url, properties);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Location (userName, locationX, locationY, locationName) " +
                            "VALUES (?, ?, ?, ?)"
            );
            preparedStatement.setString(1, userName);
            preparedStatement.setLong(2, locationX);
            preparedStatement.setFloat(3, locationY);
            preparedStatement.setString(4, locationName);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.err.println("Data inserted successfully into Location table\n");
        } catch (SQLException | IOException e) {
            System.err.println("Error inserting data into Location table " + e.getMessage());
        }
    }

    public void insertIntoPerson(String userName, String adminName, long height, EyesColor eyeColor, HairColor hairColor, Country nationality) {
        try {
            properties.load(new FileInputStream(System.getenv("PROP")));
            connectDataBase(url, properties);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Person (userName, adminName, height, eyeColor, hairColor, nationality) " +
                            "VALUES (?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, adminName);
            preparedStatement.setLong(3, height);
            preparedStatement.setObject(4, eyeColor, Types.OTHER); // Enum EyesColor
            preparedStatement.setObject(5, hairColor, Types.OTHER); // Enum HairColor
            preparedStatement.setObject(6, nationality, Types.OTHER); // Enum Country

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.err.println("Data inserted successfully into Person table\n");
        } catch (SQLException | IOException e) {
            System.err.println("Error inserting data into Person table " + e.getMessage());
        }
    }

    public void insertIntoStudyGroup(String userName, String groupName, LocalDateTime creationDate, int studentsCount, long expelledStudents, int transferredStudents, FormOfEducation formOfEducation) throws SQLException {
        String sql = "SELECT nextval('ID_SEQ') AS next_id";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            int nextId = rs.getInt("next_id");
            try {
                properties.load(new FileInputStream(System.getenv("PROP")));
                connectDataBase(url, properties);
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO StudyGroup (groupId, userName, groupName, creationDate, studentsCount, expelledStudents, transferredStudents, formOfEducation, personId) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
                preparedStatement.setLong(1, nextId);
                preparedStatement.setString(2, userName);
                preparedStatement.setString(3, groupName);
                preparedStatement.setDate(4, Date.valueOf(creationDate.toLocalDate()));
                preparedStatement.setInt(5, studentsCount);
                preparedStatement.setLong(6, expelledStudents);
                preparedStatement.setInt(7, transferredStudents);
                preparedStatement.setObject(8, formOfEducation, Types.OTHER); // Enum FormOfEducation
                preparedStatement.setLong(9, nextId);

                preparedStatement.executeUpdate();
                preparedStatement.close();
                System.err.println("Data inserted successfully into StudyGroup table\n");
            } catch (SQLException | IOException e) {
                System.err.println("Error inserting data into StudyGroup table " + e.getMessage());
            }
        }
    }

    public void deleteAllCollectionTable(String userName){
        try {
            properties.load(new FileInputStream(System.getenv("PROP")));
            connectDataBase(url, properties);

            PreparedStatement deleteFromStudyGroup = connection.prepareStatement(
                    "DELETE FROM StudyGroup WHERE userName = ?"
            );
            deleteFromStudyGroup.setString(1, userName);

            PreparedStatement deleteFromPerson = connection.prepareStatement(
                    "DELETE FROM Person WHERE userName = ?"
            );
            deleteFromPerson.setString(1, userName);

            PreparedStatement deleteFromCoordinates = connection.prepareStatement(
                    "DELETE FROM Coordinates WHERE userName = ?"
            );
            deleteFromCoordinates.setString(1, userName);

            PreparedStatement deleteFromLocation = connection.prepareStatement(
                    "DELETE FROM Location WHERE userName = ?"
            );
            deleteFromLocation.setString(1, userName);

            deleteFromStudyGroup.executeUpdate();
            deleteFromPerson.executeUpdate();
            deleteFromCoordinates.executeUpdate();
            deleteFromLocation.executeUpdate();

            deleteFromStudyGroup.close();
            deleteFromPerson.close();
            deleteFromCoordinates.close();
            deleteFromLocation.close();

            System.err.println("Collection tables successfully deleted!\n");
        } catch (SQLException | IOException e) {
            System.err.println("Error deleting collection tables " + e.getMessage());
        }
    }
}
