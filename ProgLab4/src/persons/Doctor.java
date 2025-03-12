package persons;

import abilities.*;
import enums.*;
import exceptions.CheckMedicalException;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;

public class Doctor extends LittleMen implements Heartbeat, Sniff, TryingToWake, GiveNames {
    private static final Doctor pilulkin = new Doctor("Pilulkin","Доктор", "Не ел груши");
    private Doctor(String name, String role, String ans){
        super(name, role, ans);
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void shakingVictim(Victim dunno){
        System.out.println(this.name + " трясет " + dunno.name + " за плечо");
    }

    @Override
    public void grabVictimHand(Victim dunno){
        System.out.print(this.name + " хватает " + dunno.name + " за руку ");
    }

    @Override
    public void checkingVictimPulse(){
        System.out.println("и проверяет пульс");
    }

    @Override
    public void listenVictimHeartbeat(Victim dunno){
        System.out.println(this.name + " слушает сердцебиение " + dunno.name);

    }

    @Override
    public void giveToSniffToVictim(Victim dunno) throws CheckMedicalException {
        class SniffingObject {
            final Medical medical = Medical.NASHATR;
        }
        SniffingObject sniffingMedical = new SniffingObject();
        if (sniffingMedical.medical.getMedical() != null) {
            System.out.println(this.name + " решает дать " + dunno.name + " понюхать " + sniffingMedical.medical.getMedical());
        }
        else{
            throw new CheckMedicalException();
        }
    }

    public static Doctor getPilulkin(){
        return pilulkin;
    }
}
