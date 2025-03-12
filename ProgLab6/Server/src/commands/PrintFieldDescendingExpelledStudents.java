package commands;

import models.StudyGroup;
import utility.ServerCollectionManager;
import utility.SortManager;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class PrintFieldDescendingExpelledStudents extends ServerCommand implements Serializable {
    public PrintFieldDescendingExpelledStudents(){
        super("print_field_descending_expelled_students", "вывести значения поля expelledStudents всех элементов в порядке убывания.");
    }

    @Override
    public Object executionForResponse(Object value) {
        Comparator<StudyGroup> groupComparator = new SortManager().sortExpelledStudents();
        List<StudyGroup> sortedGroupList = ServerCollectionManager.group.values().stream()
                .sorted(groupComparator).toList();

        return sortedGroupList.stream()
                .collect(Collectors.toMap(StudyGroup::getGroupId, item -> item, (a, b) -> a, LinkedHashMap::new));
    }
}
