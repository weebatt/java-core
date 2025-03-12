package commands;

import models.User;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class Quit extends ServerCommand implements Serializable {
    public Quit(){
        super("quit", "отключение клиента.");
    }

    @Override
    public Object executionForResponseQuit(Object object, User user, Socket clientSocket){
        try {
            Authorization.list.remove(user);
            clientSocket.close();
            return String.format("Client %s was successfully disconnected!", clientSocket);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
