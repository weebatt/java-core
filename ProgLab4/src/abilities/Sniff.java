package abilities;
import exceptions.CheckMedicalException;
import persons.*;

public interface Sniff{
    void giveToSniffToVictim(Victim dunno) throws CheckMedicalException;
}
