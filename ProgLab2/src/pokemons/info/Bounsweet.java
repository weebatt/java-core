package pokemons.info;
import abilities.*;
import ru.ifmo.se.pokemon.*;

public class Bounsweet extends Pokemon{
    public Bounsweet(String Name, int Lvl){
        super(Name, Lvl);
        setStats(42, 30, 38, 30, 38, 32);
        setType(Type.GRASS);
        setMove(new RazorLeaf(), new Splash());
    }
}