package org.example.commands;

import org.example.models.User;
import org.example.utility.ServerCollectionManager;

import java.io.IOException;
import java.io.Serializable;

public class Show extends ServerCommand implements Serializable {
    public Show() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        return ServerCollectionManager.group;
    }
}

