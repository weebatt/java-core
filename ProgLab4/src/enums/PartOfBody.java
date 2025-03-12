package enums;

public enum PartOfBody {
    OPEN("И открыл глаза!!!!!"),
    CLOSE(" закрыл глаза"),
    UNKNOWN(" неизвестно");

    private final String s;

    PartOfBody(String s){
        this.s = s;
    }

    public String getStringStatusPartOfBody(){
        return s;
    }

}
