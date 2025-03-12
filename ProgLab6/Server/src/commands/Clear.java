package commands;

import utility.ServerCollectionManager;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Clear extends ServerCommand implements Serializable {
    public Clear(){
        super("clear", "очистить коллекцию.");
    }

    @Override
    public Object executionForResponse(Object value) {
        File file = new File(System.getenv("MYFILE"));
        try{
            file.delete();
            file.createNewFile();
            ServerCollectionManager.group.clear();
            return "The collection was successfully cleared";
        } catch (IOException e){
            return "Error when clearing file: " + e.getMessage();
        }
    }
}
