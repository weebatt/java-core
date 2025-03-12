package commands;

import utility.ServerCommandManager;

import java.io.Serializable;

public class Help extends ServerCommand implements Serializable {
    public Help(){
        super("help", "вывести справку по доступным командам.");
    }

    @Override
    public Object executionForResponse(Object value){
        return ServerCommandManager.serverCommands;
    }
}
