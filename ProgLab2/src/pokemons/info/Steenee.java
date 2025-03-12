package pokemons.info;
import abilities.*;
import ru.ifmo.se.pokemon.*;

public class Steenee extends Bounsweet{
    public Steenee(String Name, int Lvl){
        super(Name, Lvl);
        setStats(52, 40, 48, 40, 48, 62);
        setType(Type.GRASS);
        setMove(new RazorLeaf(), new Splash(), new PlayNice());
    }
}