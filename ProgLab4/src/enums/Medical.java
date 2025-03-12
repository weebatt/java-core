package enums;

public enum Medical {
    NASHATR("нашатырь"),
    AMIAK(null);

    private String s;

    Medical(String s){
        this.s = s;
    }

    public String getMedical(){
        return s;
    }
}
