package abilities;
import ru.ifmo.se.pokemon.*;

public class FlashCannon extends SpecialMove {
    public FlashCannon() {
        super(Type.STEEL, 80,100);
    }

    private boolean flag;
    @Override
    protected void applyOppEffects(Pokemon p){
        if (Math.random() <= 0.1){
            flag = true;
            p.setMod(Stat.SPECIAL_DEFENSE, -1);
        }
    }

    @Override
    protected String describe(){
        if (flag) {return "Канонная флешка -1";}
        else {return "Канонная флешка";}
    }
}
