package pokemons.info;
import abilities.*;
import ru.ifmo.se.pokemon.*;

public class Grimer extends Pokemon{
    public Grimer(String Name, int Lvl){
        super(Name, Lvl);
        setStats(80, 80, 50, 40, 50, 25);
        setType(Type.POISON);
        setMove(new SludgeBomb(), new DoubleTeam(), new Swagger());
    }
}
