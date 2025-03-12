package abilities;
import ru.ifmo.se.pokemon.*;

public class DarkPulse extends SpecialMove {
    public DarkPulse() {
        super(Type.DARK, 80, 100);
    }

    private boolean flag;
    @Override
    protected void applyOppEffects(Pokemon p){
        if (Math.random() <= 0.2) {
            flag = true;
            Effect.flinch(p);
        }
    }

    @Override
    protected String describe(){
        if (flag) {return "Темно и страшно пульсирует";}
        else {return "Темно пульсирует";}
    }
}
