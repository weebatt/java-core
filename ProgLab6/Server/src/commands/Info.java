package commands;

import utility.ServerCollectionManager;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Date;

public class Info extends ServerCommand implements Serializable {
    public Info(){
        super("info", "вывести информацию о коллекции.");
    }

    @Override
    public Object executionForResponse(Object value){
        File file = new File(System.getenv("MYFILE"));
        Date creationTime;
        Date lastModified;
        try {
            FileTime fileTime = (FileTime) Files.getAttribute(Path.of(System.getenv("MYFILE")), "creationTime");
            creationTime = new Date(fileTime.toMillis());
            lastModified = new Date(file.lastModified());
            return ServerCollectionManager.group.getClass() + "\n" + creationTime +"\n" +  ServerCollectionManager.group.size() + "\n" + lastModified;
        } catch (IOException e) {
            return "An unexpected error occurred! " + e.getMessage();
        }
    }
}
