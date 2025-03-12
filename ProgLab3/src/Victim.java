import java.util.Scanner;

public class Victim extends LittleMen implements Sniffing, GiveNames{
    static Scanner scanner = new Scanner(System.in);
    static int amountOfPears = scanner.nextInt();

    private static final Victim victim = new Victim("Dunno", "Пострадавший", "Ел груши", amountOfPears, Status.NOSTATUS);
    Victim(String name, String role, String ans, int amtOfEatenPears, Status status){
        super(name, role, ans, amtOfEatenPears, status);
    }

    @Override
    public void victimSniffing(){
        System.out.println(this.name + " вдыхает нашатырь... и...");
    }

    @Override
    public String getName(){
        return this.name;
    }

    public static Victim getVictim(){
        return victim;
    }
}
