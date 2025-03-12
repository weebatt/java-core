import persons.*;
import enums.*;
import exceptions.*;

public class Main{
    public static void main(String[] args) throws CheckMedicalException {
        MinorCharacters guys = new MinorCharacters("Vintic and Shpuntic", "наблюдатели", "не ели"){
            {
                this.watching();
            }
            void watching(){
                System.out.println(this.name + LittleMenStatus.WATCHING.getStringStatus() + Victim.getVictim().getName());
            }
        };

        Victim.Eyes eyes = Victim.getVictim().new Eyes(PartOfBody.UNKNOWN);

        if (Victim.getVictim().getAmtOfEatenPears() > 0){
            switch (Victim.getVictim().getAmtOfEatenPears()){
                case 0:
                    Victim.getVictim().setStatus(LittleMenStatus.HUNGRY);
                    break;
                case 1,2,3,4:
                    Victim.getVictim().setStatus(LittleMenStatus.NORMAL);
                    break;
                case 5,6,7:
                    Victim.getVictim().setStatus(LittleMenStatus.CLOSEYES);
                    break;
                case 8,9,10,11:
                    Victim.getVictim().setStatus(LittleMenStatus.WHITEFACE);
                    break;
                default:
                    Victim.getVictim().setStatus(LittleMenStatus.DEATH);
                    break;
            }
        }
        else {
            throw new EatImposibleAmountOfPearsException();
        }

        switch (Victim.getVictim().getStatus()){
            case HUNGRY:
                System.out.println(LittleMenStatus.HUNGRY.getStringStatus());
                break;
            case NORMAL:
                System.out.println(LittleMenStatus.NORMAL.getStringStatus());
                break;
            case CLOSEYES:
                eyes.setEyesCondition(PartOfBody.CLOSE);
                System.out.println(LittleMenStatus.CLOSEYES.getStringStatus());
                eyes.closeEyes();
                Doctor.getPilulkin().shakingVictim(Victim.getVictim());
                System.out.println(LittleMenStatus.WAKEUP.getStringStatus());
                eyes.setEyesCondition(PartOfBody.OPEN);
                eyes.openEyes();
                break;
            case WHITEFACE:
                eyes.setEyesCondition(PartOfBody.CLOSE);
                System.out.println(LittleMenStatus.CLOSEYES.getStringStatus());
                eyes.closeEyes();
                Doctor.getPilulkin().shakingVictim(Victim.getVictim());
                System.out.println(LittleMenStatus.WHITEFACE.getStringStatus());
                Doctor.getPilulkin().grabVictimHand(Victim.getVictim());
                Doctor.getPilulkin().checkingVictimPulse();
                System.out.println(LittleMenStatus.WEAKPULSE.getStringStatus());
                Doctor.getPilulkin().listenVictimHeartbeat(Victim.getVictim());
                System.out.println(LittleMenStatus.NOHEARTBEAT.getStringStatus());
                Doctor.getPilulkin().giveToSniffToVictim(Victim.getVictim());
                Victim.getVictim().victimSniffing();
                System.out.println(LittleMenStatus.WAKEUP.getStringStatus());
                eyes.setEyesCondition(PartOfBody.OPEN);
                eyes.openEyes();
                break;
            case DEATH:
                System.out.println(LittleMenStatus.CLOSEYES.getStringStatus());
                eyes.closeEyes();
                Doctor.getPilulkin().shakingVictim(Victim.getVictim());
                System.out.println(LittleMenStatus.WHITEFACE.getStringStatus());
                Doctor.getPilulkin().grabVictimHand(Victim.getVictim());
                Doctor.getPilulkin().checkingVictimPulse();
                System.out.println(LittleMenStatus.WEAKPULSE.getStringStatus());
                Doctor.getPilulkin().listenVictimHeartbeat(Victim.getVictim());
                System.out.println(LittleMenStatus.NOHEARTBEAT.getStringStatus());
                Doctor.getPilulkin().giveToSniffToVictim(Victim.getVictim());
                Victim.getVictim().victimSniffing();
                System.out.println(LittleMenStatus.DEATH.getStringStatus());
        }
    }

}