import pokemons.info.*;
import ru.ifmo.se.pokemon.*;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Magearna p1 = new Magearna("RoboGirl", 1);
        Grimer p2 = new Grimer("Kucha", 1);
        Muk p3 = new Muk("BigKucha", 1);
        Bounsweet p4 = new Bounsweet("LittleBerry", 1);
        Steenee p5 = new Steenee("MediumBerry", 1);
        Tsareena p6 = new Tsareena("BigBerry", 1);
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }
}