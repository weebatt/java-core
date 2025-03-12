package org.example.commands;

import org.example.models.User;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.sql.SQLException;

public abstract class ServerCommand implements Serializable {
    private final String commandName;
    private final String description;

    public ServerCommand(String commandName, String description){
        this.commandName = commandName;
        this.description = description;
    }



    public String getCommandName(){
        return commandName;
    }

    public String getDescription(){
        return description;
    }


    public Object executionForResponse(Object object, User user) throws IOException, SQLException {
        return null;
    }

    public void executionCollectionCommands(Object object, User user) throws SQLException, IOException {}

    public Object executionForResponseQuit(Object object, User user, Socket clientSocket){
        return null;
    }

    @Override
    public String toString(){
        return getDescription();
    }
}
