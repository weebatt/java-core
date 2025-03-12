package commands;

import models.Command;
import utility.CollectionManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Date;

/**
 * Команда 'info'. Выводит информацию о коллекции.
 * @author butareyka
 */
public class Info extends Command {
    public Info(){
        super("info", "вывести информацию о коллекции");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String value){
        try {
            File file = new File(System.getenv("MYFILE"));
            FileTime fileTime = (FileTime) Files.getAttribute(Path.of(System.getenv("MYFILE")), "creationTime");
            Date creationTime = new Date(fileTime.toMillis());
            Date lastModified = new Date(file.lastModified());
            System.out.print(CollectionManager.group.getClass() + "\n" + creationTime +"\n" +  CollectionManager.group.size() + "\n" + lastModified + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
