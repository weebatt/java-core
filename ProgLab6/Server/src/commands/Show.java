package commands;

import models.StudyGroup;
import utility.ServerCollectionManager;
import utility.SortManager;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Show extends ServerCommand implements Serializable {
    public Show() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении.");
    }

    @Override
    public Object executionForResponse(Object value) {
        Comparator<StudyGroup> groupComparator = new SortManager().sortLocation();
        File file = new File(System.getenv("MYFILE"));
        List<StudyGroup> sortedGroupList = ServerCollectionManager.group.values().stream()
                .sorted(groupComparator).toList();

        if (file.length() == 0 && sortedGroupList.isEmpty()) {
            return "Collection is empty!";
        } else {
            return sortedGroupList.stream()
                    .collect(Collectors.toMap(StudyGroup::getGroupId, item -> item, (a, b) -> a, LinkedHashMap::new));
        }

//        Comparator<StudyGroup> groupComparator = new SortManager().sortId();
//        List<StudyGroup> sortedListGroup = new ArrayList<>(ServerCollectionManager.group.values());
//        Map<Long, StudyGroup> sortedMapGroup = new LinkedHashMap<>();
//
//        if (file.length() == 0 && ServerCollectionManager.group.isEmpty()) {
//            return "Collection is empty!";
//        }
//        else{
//            Collections.sort(sortedListGroup, groupComparator);
//            for (StudyGroup item : sortedListGroup) {
//                sortedMapGroup.put(item.getGroupId(), item);
//            }
//            return sortedMapGroup;
//        }
    }
}

