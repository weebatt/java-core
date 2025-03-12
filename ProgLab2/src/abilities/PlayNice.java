package abilities;
import ru.ifmo.se.pokemon.*;

public class PlayNice extends StatusMove {
    public PlayNice() {
        super(Type.NORMAL, 0,100);
    }

    @Override
    protected void applyOppEffects(Pokemon p){
        p.setMod(Stat.ATTACK, -1);
    }

    @Override
    protected String describe(){
        return "Хорошо сыграно, -1";
    }
}
