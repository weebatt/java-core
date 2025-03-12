package commands;

import models.Command;
import models.StudyGroup;
import utility.CollectionManager;
import utility.FileManager;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Команда 'save'. Сохраняет коллекцию в файл.
 * @author butareyka
 */
public class Save extends Command {
    public Save(){
        super("save", "сохранить коллекцию в файл");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String str){
        StringBuilder data = new StringBuilder();
        FileManager fileManager = new FileManager();

        try{
            Set<Long> keys = CollectionManager.group.keySet();
            Iterator<Long> iterator = keys.iterator();
            Long lastKey = 1L;
            while (iterator.hasNext()) {
                lastKey = iterator.next();
            }

            for (Map.Entry<Long, StudyGroup> item : CollectionManager.group.entrySet()) {
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
            System.out.println("Данные успешно сохранены");
        }catch (Exception e){
            System.out.println("Ошибка при передаче данных parseCollectionToCSV");
        }
    }
}
