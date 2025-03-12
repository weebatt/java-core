package models;

import java.io.Serial;
import java.io.Serializable;

public class Location implements Serializable {
    private long x;
    private Double y; //Поле не может быть null
    private String locationName; //Длина строки не должна быть больше 979, Поле не может быть null

    public Location(String locationName, long x, Double y){
        this.x = x;
        this.y = y;
        this.locationName = locationName;
    }

    public Location() {
    }

    @Serial
    private static final long serialVersionUID = 3L;

    public long getX() {
            return x;
        }
    public void setX(long x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }
    public void setY(Double y) {
        this.y = y;
    }

    public String getLocationName() {
        return locationName;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public long convertStringToCoordinatesX(String str){
        String[] str_coord;
        str_coord = str.split(";");
        return Long.parseLong(str_coord[0]);
    }

    public Double convertStringToCoordinatesY(String str){
        String[] str_coord;
        str_coord = str.split(";");
        return Double.parseDouble(str_coord[1]);
    }

    @Override
    public String toString(){
        return "locationName: " + locationName + ", location: " + x + ";" + y;
    }
}