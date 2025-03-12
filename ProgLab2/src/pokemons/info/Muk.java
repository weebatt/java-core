package pokemons.info;
import abilities.*;
import ru.ifmo.se.pokemon.*;

public class Muk extends Grimer {
    public Muk(String Name, int Lvl){
        super(Name, Lvl);
        setStats(105, 105, 75, 65, 100, 50);
        setType(Type.POISON);
        setMove(new SludgeBomb(), new DoubleTeam(), new Swagger(), new DarkPulse());
    }
}
