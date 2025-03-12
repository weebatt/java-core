package exceptions;

public class CheckMedicalException extends Exception{
    public CheckMedicalException(){
        super("Такого поля в Medical не существует");
    }
}
