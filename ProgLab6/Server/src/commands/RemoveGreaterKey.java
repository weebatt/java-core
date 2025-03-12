package commands;

import models.StudyGroup;
import utility.ServerCollectionManager;
import utility.SortManager;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class RemoveGreaterKey extends ServerCommand implements Serializable {
    public RemoveGreaterKey(){
        super("remove_greater_key", "удалить из коллекции все элементы, ключ которых превышает заданный.");
    }

    @Override
    public Object executionForResponse(Object value) {
        String valueStr = (String) value;
        Long key = Long.parseLong(valueStr);

        List<StudyGroup> sortedGroupList = ServerCollectionManager.group.values().stream()
                .filter(item -> item.getGroupId() <= key)
                .collect(Collectors.toList());

        Comparator<StudyGroup> groupComparator = new SortManager().sortId();
        Collections.sort(sortedGroupList, groupComparator);

        return sortedGroupList.stream()
                .collect(Collectors.toMap(StudyGroup::getGroupId, item -> item, (a, b) -> a, LinkedHashMap::new));
    }
}
