package commands;

import models.StudyGroup;
import utility.ServerCollectionManager;
import utility.SortManager;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class RemoveGreater extends ServerCommand implements Serializable {
    public RemoveGreater(){
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный.");
    }

    @Override
    public Object executionForResponse(Object value) {
        new Insert().executionForResponse(value);
        new Save().executionForResponse(null);

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

        return sortedGroupList.stream()
                .collect(Collectors.toMap(StudyGroup::getGroupId, item -> item, (a, b) -> a, LinkedHashMap::new));
    }
}