package pokemons.info;
import abilities.*;
import ru.ifmo.se.pokemon.*;

public class Magearna extends Pokemon{
    public Magearna(String Name, int Lvl){
        super(Name, Lvl);
        setStats(80, 95, 115, 130, 115, 65);
        setType(Type.STEEL, Type.FAIRY);
        setMove(new FlashCannon(), new Confide(), new MetalClaw(), new NightSlash());
    }
}
