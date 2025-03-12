package abilities;
import ru.ifmo.se.pokemon.*;

public class SludgeBomb extends SpecialMove {
    public SludgeBomb(){
        super(Type.POISON, 90, 100);
    }

    private boolean flag;
    @Override
    public void applyOppEffects(Pokemon p) {
        if ((Math.random() <= 0.3) & (!p.hasType(Type.POISON) | !p.hasType(Type.STEEL))){
            flag = true;
            Effect.poison(p);
        }
    }

    @Override
    protected String describe(){
        if(flag){return "дамагает и травит";}
        else{return "дамагает";}
    }
}
