package org.example.models;

import java.io.Serial;
import java.io.Serializable;

public class Coordinates implements Serializable {
    private double x; //Значение поля должно быть больше -339, Поле не может быть null
    private int y;
    private String userName;

    public Coordinates(double x, int y, String userName){
        this.x = x;
        this.y = y;
        this.userName = userName;
    }

    public Coordinates() {
    }

    @Serial
    private static final long serialVersionUID = 8L;

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString(){
        return x + ";" + y;
    }
}