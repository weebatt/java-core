package models;

/**
 * Класс с конструктором для создания GroupAdmin'a.
 * @author butareyka
 */
public class Person {
    private String adminName; //Поле не может быть null, Строка не может быть пустой
    private long height; //Значение поля должно быть больше 0
    private EyesColor eyeColor; //Поле может быть null
    private HairColor hairColor; //Поле не может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле может быть null

    public Person(String adminName, long height, EyesColor eyeColor, HairColor hairColor, Country nationality, Location location){
        this.adminName = adminName;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public Person(){}

    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public long getHeight() {
        return height;
    }
    public void setHeight(long height) {
        this.height = height;
    }

    public EyesColor getEyeColor() {
        return eyeColor;
    }
    public void setEyeColor(EyesColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public HairColor getHairColor() {
        return hairColor;
    }
    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public Country getNationality() {
        return nationality;
    }
    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString(){
        return "adminName: " + adminName +
                ", height: " + height +
                ", eyeColor: " + eyeColor +
                ", hairColor: " + hairColor +
                ", nationality: " + nationality +
                ", " + location;
    }
}