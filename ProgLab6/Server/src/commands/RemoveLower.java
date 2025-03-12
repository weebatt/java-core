package commands;

import models.StudyGroup;
import utility.ServerCollectionManager;
import utility.SortManager;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class RemoveLower extends ServerCommand implements Serializable {
    public RemoveLower(){
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный.");
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
        sortedGroupList = sortedGroupList.stream()
                .filter(item -> Objects.equals(item.getGroupId(), finalLastEntry.getKey()))
                .toList();

        return sortedGroupList.stream()
                .collect(Collectors.toMap(StudyGroup::getGroupId, item -> item, (a, b) -> a, LinkedHashMap::new));
    }
}
        
//        Comparator<StudyGroup> groupComparator = new SortManager().sortLocation();
//        List<StudyGroup> sortedGroupList = new ArrayList<>(ServerCollectionManager.group.values());
//
//        Collections.sort(sortedGroupList, groupComparator);
//        ListIterator<StudyGroup> iterator1 = sortedGroupList.listIterator(sortedGroupList.size());
//        while (iterator1.hasPrevious()){
//            StudyGroup item = iterator1.previous();
//            Long key = item.getGroupId();
//
//            assert lastEntry != null;
//            if (!Objects.equals(key, lastEntry.getKey())){
//                iterator1.remove();
//            }
//            else {
//                break;
//            }
//        }
//
//        Map<Long, StudyGroup> sortedGroupMap = new LinkedHashMap<>();
//        for (StudyGroup item: sortedGroupList){
//            sortedGroupMap.put(item.getGroupId(), item);
//        }
//        return sortedGroupMap;
//    }
//}
