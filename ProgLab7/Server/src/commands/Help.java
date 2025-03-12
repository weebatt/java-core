package commands;

import models.User;
import utility.ServerCommandManager;

import java.io.Serializable;

public class Help extends ServerCommand implements Serializable {
    public Help(){
        super("help", "вывести справку по доступным командам.");
    }

    @Override
    public Object executionForResponse(Object value, User user){
        if (Authorization.list.contains(user.getUserName())){
            return ServerCommandManager.serverCommands;
        } else {
            return "You need to reg or log_in";
        }
    }
}
