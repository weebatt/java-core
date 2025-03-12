package commands;

import models.StudyGroup;
import models.User;
import utility.ServerCollectionManager;
import utility.SortManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class PrintFieldDescendingExpelledStudents extends ServerCommand implements Serializable {
    public PrintFieldDescendingExpelledStudents(){
                super("print_field_descending_expelled_students", "вывести значения поля expelledStudents всех элементов в порядке убывания.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        if (Authorization.list.contains(user.getUserName())) {
            new LoadCollection().executionForResponse(null, user);
            Comparator<StudyGroup> groupComparator = new SortManager().sortExpelledStudents();
            List<StudyGroup> sortedGroupList = ServerCollectionManager.group.values().stream()
                    .sorted(groupComparator).toList();

            return sortedGroupList.stream()
                    .collect(Collectors.toMap(StudyGroup::getGroupId, item -> item, (a, b) -> a, LinkedHashMap::new));
        } else {
            return "You need to reg or log_in";
        }
    }
}
