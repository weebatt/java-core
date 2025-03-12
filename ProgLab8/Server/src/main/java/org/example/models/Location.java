package org.example.models;

import java.io.Serial;
import java.io.Serializable;

public class Location implements Serializable {
    private long x;
    private float y; //Поле не может быть null
    private String locationName; //Длина строки не должна быть больше 979, Поле не может быть null
    private String userName;

    public Location(String locationName, long x, float y, String userName){
        this.x = x;
        this.y = y;
        this.locationName = locationName;
        this.userName = userName;
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

    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }

    public String getXAndY(){
        return x + ";" + y;
    }

    public String getLocationName() {
        return locationName;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString(){
        return "locationName: " + locationName + ", location: " + x + ";" + y;
    }
}