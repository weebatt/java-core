package commands;

import models.Command;
import utility.CollectionManager;
import utility.FileManager;

/**
 * Команда 'remove_key'. Удаляет элемент из коллекции по его ключу.
 * @author butareyka
 */
public class RemoveKey extends Command {
    public RemoveKey(){
        super("remove_key", "удалить элемент из коллекции по его ключу");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String value) {
        Long key = Long.parseLong(value);
        if (CollectionManager.group.containsKey(key)){
            CollectionManager.group.remove(key);
            new Save().executionResponse("");
            if (FileManager.fileGroup.containsKey(key)){
                FileManager.fileGroup.remove(key);
            }
            new FileManager().convertStringToCollection();
            System.out.println("Элемент по заданному ключу " + key + " был успешно удален!");
        }
        else {
            System.out.println("Заданного ключа не существует!");
        }
    }
}
