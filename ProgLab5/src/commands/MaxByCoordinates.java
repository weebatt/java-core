package commands;

import models.Command;
import models.StudyGroup;
import utility.CollectionManager;
import java.util.Map;

/**
 * Команда 'max_by_coordinates'. Выводит любой объект из коллекции, значение поля coordinates которого является максимальным.
 * @author butareyka
 */
public class MaxByCoordinates extends Command {
    public MaxByCoordinates() {
        super("max_by_coordinates", "вывести любой объект из коллекции, значение поля coordinates которого является максимальным");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String value) {
        Long idElementWithMaxCoordinates = 0L;
        double xMax = 0;
        int yMax = 0;
        double prevXMax = 0;
        for (Map.Entry<Long, StudyGroup> item: CollectionManager.group.entrySet()){
            if (item.getValue().getCoordinates().getX() > xMax){
                prevXMax = xMax;
                xMax = item.getValue().getCoordinates().getX();
            }
            else {
                continue;
            }
            if (item.getValue().getCoordinates().getY() > yMax){
                yMax = item.getValue().getCoordinates().getY();
                idElementWithMaxCoordinates = item.getKey();
            }
            else {
                xMax = prevXMax;
            }
        }
        System.out.println("{" + idElementWithMaxCoordinates + "=" + CollectionManager.group.get(idElementWithMaxCoordinates) + "}");
    }
}

