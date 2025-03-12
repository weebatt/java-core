package commands;

import models.StudyGroup;
import utility.FileManager;
import utility.ServerCollectionManager;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Save extends ServerCommand implements Serializable {
    public Save(){
        super("save", "сохранить коллекцию в файл.");
    }

    @Override
    public Object executionForResponse(Object str){
        StringBuilder data = new StringBuilder();
        FileManager fileManager = new FileManager();

        try{
            Set<Long> keys = ServerCollectionManager.group.keySet();
            Iterator<Long> iterator = keys.iterator();
            Long lastKey = 1L;
            while (iterator.hasNext()) {
                lastKey = iterator.next();
            }

            for (Map.Entry<Long, StudyGroup> item : ServerCollectionManager.group.entrySet()) {
                Long key = item.getKey();
                StudyGroup value = item.getValue();
                if (!item.getKey().equals(lastKey)){
                    data.append(key).append(",").append(value).append(',');
                }
                else{
                    data.append(key).append(",").append(value);
                }
            }
            fileManager.parseCollectionToCSV(data.toString());
            System.out.println("Data saved successfully");
        }catch (Exception e){
            System.out.println("Error while passing data parseCollectionToCSV");
        }
        return null;
    }
}
