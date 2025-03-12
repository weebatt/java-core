package models;

import java.io.Serial;
import java.io.Serializable;

public class Coordinates implements Serializable {
    private double x; //Значение поля должно быть больше -339, Поле не может быть null
    private int y;

    public Coordinates(double x, int y){
        this.x = x;
        this.y = y;
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

    public Coordinates convertStringToCoordinates(String str){
        String[] str_coord;
        str_coord = str.split(";");
        return new Coordinates(Double.parseDouble(str_coord[0]), Integer.parseInt(str_coord[1]));
    }

    @Override
    public String toString(){
        return x + ";" + y;
    }
}