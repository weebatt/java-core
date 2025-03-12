package org.example.utility;

import org.example.models.StudyGroup;

import java.util.LinkedHashMap;
import java.util.Map;

public class ServerCollectionManager {
    public static Map<Long, StudyGroup> group = new LinkedHashMap<>();
    StudyGroup studyGroup = new StudyGroup();

    private Long group_id;
    public void addGroupId(){
        boolean f = true;
        group_id = 1L;
        while (f){
            if (group.containsKey(group_id)) {
                group_id += 1;
            } else{
                f = false;
            }
        }
        studyGroup.setGroupId(group_id);
    }

    public Long takeGroupId(){
        return group_id;
    }
}
