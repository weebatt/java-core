package pokemons.info;
import abilities.*;
import ru.ifmo.se.pokemon.*;

public class Tsareena extends Steenee {
    public Tsareena(String Name, int Lvl){
        super(Name, Lvl);
        setStats(72, 120, 98, 50, 98, 72);
        setType(Type.GRASS);
        setMove(new RazorLeaf(), new Splash(), new PlayNice());
    }
}