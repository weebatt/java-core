package abilities;
import ru.ifmo.se.pokemon.*;

public class MetalClaw extends PhysicalMove {
    public MetalClaw(){
        super(Type.STEEL, 50,95);
    }

    private boolean flag;
    @Override
    protected void applySelfEffects(Pokemon p){
        if (Math.random() <= 0.1) {
            flag = true;
            p.setMod(Stat.ATTACK, +1);
        }
    }

    @Override
    protected String describe(){
        if (flag) {return "Мет коготь +1";}
        else {return "Мет коготь";}
    }
}
