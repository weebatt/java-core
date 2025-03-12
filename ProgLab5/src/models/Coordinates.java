package models;

/**
 * Класс координат.
 * @author butareyka
 */
public class Coordinates{
    private double x; //Значение поля должно быть больше -339, Поле не может быть null
    private int y;


    public Coordinates(double x, int y){
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

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

    /**
     * Выполняет перевод координат из String to Coordinates.
     * @return новый объект класса Coordinates
     */
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