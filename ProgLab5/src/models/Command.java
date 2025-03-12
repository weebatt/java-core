package models;

/**
 * Абстрактный класс команд.
 * @author butareyka
 */
public abstract class Command {
    private final String commandName;
    private final String description;

    public Command(String commandName, String description){
        this.commandName = commandName;
        this.description = description;
    }

    public String getCommandName(){
        return commandName;
    }

    public String getDescription(){
        return description;
    }
    

    public void executionResponse(String str){}
}
