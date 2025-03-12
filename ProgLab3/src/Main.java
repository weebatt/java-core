import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите количество съеденных груш Незнайкой: ");

        if (Victim.getVictim().getAmtOfEatenPears() > 0){
            switch (Victim.getVictim().getAmtOfEatenPears()){
                case 0:
                    Victim.getVictim().setStatus(Status.HUNGRY);
                    break;
                case 1,2,3,4:
                    Victim.getVictim().setStatus(Status.NORMAL);
                    break;
                case 5,6,7:
                    Victim.getVictim().setStatus(Status.CLOSEYES);
                    break;
                case 8,9,10,11:
                    Victim.getVictim().setStatus(Status.WHITEFACE);
                    break;
                default:
                    Victim.getVictim().setStatus(Status.DEATH);
                    break;
            }
        }
        else{
            System.out.println("О нет! Ты кушаешь негативные груши!");
        }


        switch (Victim.getVictim().getStatus()){
            case HUNGRY:
                System.out.println(Status.HUNGRY.getStringStatus());
                break;
            case NORMAL:
                System.out.println(Status.NORMAL.getStringStatus());
                break;
            case CLOSEYES:
                System.out.println(Status.CLOSEYES.getStringStatus());
                Doctor.getPilulkin().shakingVictim(Victim.getVictim());
                System.out.println(Status.WAKEUP.getStringStatus());
                break;
            case WHITEFACE:
                System.out.println(Status.CLOSEYES.getStringStatus());
                Doctor.getPilulkin().shakingVictim(Victim.getVictim());
                System.out.println(Status.WHITEFACE.getStringStatus());
                Doctor.getPilulkin().grabVictimHand(Victim.getVictim());
                Doctor.getPilulkin().checkingVictimPulse();
                System.out.println(Status.WEAKPULSE.getStringStatus());
                Doctor.getPilulkin().listenVictimHeartbeat(Victim.getVictim());
                System.out.println(Status.NOHEARTBEAT.getStringStatus());
                Doctor.getPilulkin().giveToSniffToVictim(Victim.getVictim());
                Victim.getVictim().victimSniffing();
                System.out.println(Status.WAKEUP.getStringStatus());
                break;
            case DEATH:
                System.out.println(Status.CLOSEYES.getStringStatus());
                Doctor.getPilulkin().shakingVictim(Victim.getVictim());
                System.out.println(Status.WHITEFACE.getStringStatus());
                Doctor.getPilulkin().grabVictimHand(Victim.getVictim());
                Doctor.getPilulkin().checkingVictimPulse();
                System.out.println(Status.WEAKPULSE.getStringStatus());
                Doctor.getPilulkin().listenVictimHeartbeat(Victim.getVictim());
                System.out.println(Status.NOHEARTBEAT.getStringStatus());
                Doctor.getPilulkin().giveToSniffToVictim(Victim.getVictim());
                Victim.getVictim().victimSniffing();
                System.out.println(Status.DEATH.getStringStatus());

        }
    }
}