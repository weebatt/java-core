package commands;

import daba.DataBaseManager;
import models.StudyGroup;
import models.User;
import utility.ServerCollectionManager;
import utility.SortManager;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Show extends ServerCommand implements Serializable {
    public Show() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении.");
    }

    @Override
    public Object executionForResponse(Object value, User user) {
        if (Authorization.list.contains(user.getUserName())) {
            try {
                new LoadCollection().executionForResponse(null, user);
                return new DataBaseManager().readFromDataBase();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return "You need to reg or log_in";
        }
    }
}

