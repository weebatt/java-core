package persons;

import abilities.*;
import enums.LittleMenStatus;
import enums.PartOfBody;

import java.util.Scanner;

public class Victim extends LittleMen implements Sniffing, GiveNames{
    static Scanner scanner = new Scanner(System.in);
    public static class Num{
        static int num;
        static int setNumOfPears() {
            System.out.println("Введите количество груш:");
            num = scanner.nextInt();
            return num;
        }
    }

    public static int amountOfPears = Num.setNumOfPears();
    private static final Victim victim = new Victim("Dunno", "Пострадавший", "Ел груши", amountOfPears, LittleMenStatus.NOSTATUS);

    private Victim(String name, String role, String ans, int amtOfEatenPears, LittleMenStatus littleMenStatus){
        super(name, role, ans, amtOfEatenPears, littleMenStatus);
    }

    @Override
    public void victimSniffing(){
        System.out.println(this.name + " вдыхает нашатырь... и...");
    }

    @Override
    public String getName(){
        return this.name;
    }


    public class Eyes{
        private PartOfBody condition;

        public Eyes(PartOfBody condition){
            this.condition = condition;
        }

        public void closeEyes(){
            System.out.println("Кроме того, что " + getName() + " " + LittleMenStatus.CLOSEYES.getStringStatus() + ", он еще" + condition.getStringStatusPartOfBody());
        }

        public void openEyes(){
            System.out.println(condition.getStringStatusPartOfBody());
        }

        public void setEyesCondition(PartOfBody condition){
            this.condition = condition;
        }
    }

    public static Victim getVictim(){
        return victim;
    }
}
