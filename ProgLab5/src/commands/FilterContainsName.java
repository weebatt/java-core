package commands;

import models.Command;
import models.StudyGroup;
import utility.CollectionManager;

import java.util.Map;

/**
 * Команда 'filter_contains_name'. Проверяет наличие подстроки в поле name.
 * @author butareyka
 */
public class FilterContainsName extends Command {
    public FilterContainsName(){
        super("filter_contains_name", "вывести элементы, значение поля name которых содержит заданную подстроку");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String subStr) {
        int counter = 0;
        for (Map.Entry<Long, StudyGroup> item: CollectionManager.group.entrySet()){
            if (item.getValue().getGroupName().contains(subStr)){
                System.out.println("{" + item.getKey() + "=" + item.getValue() + "}");
                counter++;
            }
        }
        if (counter == 0){
            System.out.println("Нет соответствующего элемента! ");
        }
    }
}
