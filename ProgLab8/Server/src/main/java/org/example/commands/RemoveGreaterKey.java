package org.example.commands;

import org.example.daba.DataBaseManager;
import org.example.models.StudyGroup;
import org.example.models.User;
import org.example.utility.ServerCollectionManager;
import org.example.utility.SortManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class RemoveGreaterKey extends ServerCommand implements Serializable {
    public RemoveGreaterKey(){
        super("remove_greater_key", "удалить из коллекции все элементы, ключ которых превышает заданный.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.checkUser(user.getUserName())) {
            String valueStr = (String) value;
            Long key = Long.parseLong(valueStr);

            List<StudyGroup> sortedGroupList = ServerCollectionManager.group.values().stream()
                    .filter(item -> item.getGroupId() <= key)
                    .collect(Collectors.toList());

            Comparator<StudyGroup> groupComparator = new SortManager().sortId();
            Collections.sort(sortedGroupList, groupComparator);

            return "1";
        } else {
            return "You need to reg or log_in";
        }
    }
}
