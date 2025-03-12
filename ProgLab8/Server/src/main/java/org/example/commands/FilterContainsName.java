package org.example.commands;

import org.example.daba.DataBaseManager;
import org.example.models.StudyGroup;
import org.example.models.User;
import org.example.utility.ServerCollectionManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class FilterContainsName extends ServerCommand implements Serializable {
    public FilterContainsName(){
        super("filter_contains_name", "вывести элементы, значение поля name которых содержит заданную подстроку.");
    }

    @Override
    public Object executionForResponse(Object value, User user) throws IOException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.checkUser(user.getUserName())){
            int counter = 0;
            String subStr = (String) value;
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<Long, StudyGroup> item: ServerCollectionManager.group.entrySet()){
                if (item.getValue().getGroupName().contains(subStr)){
                    stringBuilder.append("{").append(item.getKey()).append("=").append(item.getValue()).append("}");
                    counter++;
                }
            }
            if (counter == 0){
                return "No element matching substring " + subStr;
            }
            return stringBuilder.toString();
        } else{
            return "You need to reg or log_in";
        }
    }
}
