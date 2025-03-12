package commands;

import models.Command;
import utility.CollectionManager;

import java.io.File;
import java.io.IOException;

/**
 * Команда 'clear'. Очищает коллекцию.
 * @author butareyka
 */

public class Clear extends Command {
    public Clear(){
        super("clear", "очистить коллекцию");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String value) {
        File file = new File(System.getenv("MYFILE"));
        try{
            file.delete();
            file.createNewFile();
            CollectionManager.group.clear();
            System.out.println("Коллекция успешно очистилась");
        } catch (IOException e){
            System.err.println("Ошибка при очистке файла: " + e.getMessage());
        }
    }
}
