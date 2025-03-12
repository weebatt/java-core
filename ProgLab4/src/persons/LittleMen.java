package persons;

import enums.LittleMenStatus;
import enums.PartOfBody;

import java.util.Objects;

public abstract class LittleMen implements abilities.LittleMenStatus {
    protected String name;
    private final String role;
    private final String ans;
    private int amtOfEatenPears;
    private LittleMenStatus littleMenStatus;

    public LittleMen(String name, String role, String ans, int amtOfEatenPears, LittleMenStatus littleMenStatus ) {
        this.name = name;
        this.role = role;
        this.ans = ans;
        this.amtOfEatenPears = amtOfEatenPears;
        this.littleMenStatus = littleMenStatus;
    }

    public LittleMen(String name, String role, String ans) {
        this.name = name;
        this.role = role;
        this.ans = ans;
    }

    @Override
    public String toString(){
        return  "LittleMan{" +
                "Name '" + name + '\'' +
                ", Role '" + role + '\'' +
                ", Answer '" + ans + '\'' +
                ", LittleMenStatus" + littleMenStatus + '\'' +
                ", Amount of eaten pears = " + amtOfEatenPears +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LittleMen shorty = (LittleMen) obj;
        return Objects.equals(this.ans, shorty.ans) && Objects.equals(this.name, shorty.name) && Objects.equals(this.role, shorty.role);
    }

    @Override
    public int hashCode(){
        System.out.print(Objects.hashCode(ans));
        return Objects.hash(ans, name, role);
    }

    @Override
    public void setStatus(LittleMenStatus littleMenStatus){
        this.littleMenStatus = littleMenStatus;
    }

    @Override
    public LittleMenStatus getStatus(){
        return this.littleMenStatus;
    }

    public int getAmtOfEatenPears(){
        return amtOfEatenPears;
    }
}
