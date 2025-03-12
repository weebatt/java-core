package commands;


import models.Command;
import utility.Invoker;

public class NewCommand extends Command {
    public NewCommand(){
        super("newCommand", "вывести Hello World");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String value){
        System.out.println("Подозрение на рекурсию! Введите следующую команду: ");
        new Invoker().invoke();
    }
}
