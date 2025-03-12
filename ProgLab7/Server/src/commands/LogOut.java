package commands;

import models.User;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class LogOut extends ServerCommand implements Serializable {
    public LogOut(){
        super("log_out", "отключение клиента.");
    }

    @Override
    public Object executionForResponseQuit(Object object, User user, Socket clientSocket){
        return null;
    }
}
