package exceptions;

public class EatImposibleAmountOfPearsException extends RuntimeException {
    public EatImposibleAmountOfPearsException(){
        super("О нет! Ты кушаешь негативные груши!");
    }
}
