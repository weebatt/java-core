import java.util.Objects;

public class LittleMen extends LittleMenStatus{
    protected String name;
    private String role;
    private String ans;
    private int amtOfEatenPears;
    private Status status;

    public LittleMen(String name, String role, String ans, int amtOfEatenPears, Status status) {
        this.name = name;
        this.role = role;
        this.ans = ans;
        this.amtOfEatenPears = amtOfEatenPears;
        this.status = status;
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
                ", Status" + status + '\'' +
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
    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public Status getStatus(){
        return this.status;
    }


    public int getAmtOfEatenPears(){
        return amtOfEatenPears;
    }
}
