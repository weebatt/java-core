package models;

/**
 * Класс локации GroupAdmin'a.
 * @author butareyka
 */
public class Location {
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

    /**
     * Выполняет перевод координат из String to Coordinates для значения x.
     * @return x
     */
    public long convertStringToCoordinatesX(String str){
        String[] str_coord;
        str_coord = str.split(";");
        return Long.parseLong(str_coord[0]);
    }

    /**
     * Выполняет перевод координат из String to Coordinates для значения y.
     * @return y
     */
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