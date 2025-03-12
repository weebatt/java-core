package commands;

import models.Command;
import utility.CommandManager;
import java.util.Map;

/**
 * Команда 'help'. Выводит справку по доступным командам.
 * @author butareyka
 */
public class Help extends Command {
    public Help(){
        super("help", "вывести справку по доступным командам");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String value){
        System.out.println("Вы можете использовать эти команды: ");
        for(Map.Entry<String, Command> item : CommandManager.commands.entrySet()) {
            System.out.println(item.getKey() + " - " + item.getValue().getDescription());
            System.out.println(item.getKey() + item.getValue().getDescription());
        }
    }
}
