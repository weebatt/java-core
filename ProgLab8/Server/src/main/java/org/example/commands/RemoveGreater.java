package org.example.commands;

import org.example.daba.DataBaseManager;
import org.example.models.StudyGroup;
import org.example.models.User;
import org.example.utility.ServerCollectionManager;
import org.example.utility.SortManager;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class RemoveGreater extends ServerCommand implements Serializable {
    public RemoveGreater(){
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException, SQLException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.checkUser(user.getUserName())) {
            new Insert().executionForResponse(value, user);
            new Save().executionForResponse(null, user);

            Map.Entry<Long, StudyGroup> lastEntry = null;
            for (Map.Entry<Long, StudyGroup> entry : ServerCollectionManager.group.entrySet()) {
                lastEntry = entry;
            }

            Comparator<StudyGroup> groupComparator = new SortManager().sortLocation();
            List<StudyGroup> sortedGroupList = ServerCollectionManager.group.values().stream()
                    .sorted(groupComparator)
                    .collect(Collectors.toList());

            Map.Entry<Long, StudyGroup> finalLastEntry = lastEntry;
            sortedGroupList = sortedGroupList.stream().filter(item -> !Objects.equals(item.getGroupId(), finalLastEntry.getKey())).toList();

            return "1";
        } else {
            return "You need to reg or log_in";
        }
    }
}