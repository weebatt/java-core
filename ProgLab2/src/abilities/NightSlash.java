package abilities;
import ru.ifmo.se.pokemon.*;

public class NightSlash extends PhysicalMove {
    public NightSlash() {
        super(Type.DARK, 70, 100);
    }

    private boolean flag;
    @Override
    protected double calcCriticalHit(Pokemon att, Pokemon def){
        if (Math.random() <= att.getStat(Stat.SPEED) / 512){
            flag = true;
            return 2;
        }
        else{
            flag = false;
            return 1;
        }
    }


    @Override
    protected String describe(){
        if (flag) {return "ННыЫыЫыыААаАаАаа :D";}
        else {return "не ННыЫыЫыыААаАаАаа :(";}
    }

}
