package commands;

import models.Command;
import models.Person;
import models.StudyGroup;
import utility.CollectionManager;

/**
 * Команда 'insert'. Добавляет новый элемент с заданным ключом.
 * @author butareyka
 */
public class Insert extends Command {
    public Insert(){
        super("insert", "добавить новый элемент с заданным ключом");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String value) {
        CollectionManager collectionManager = new CollectionManager();
        StudyGroup gr = new StudyGroup(collectionManager.addGroupId(), collectionManager.addGroupName(), collectionManager.addCreationDate(), collectionManager.addCoordinates(), collectionManager.addStudentsCount(), collectionManager.addExpelledStudents(), collectionManager.addTransferredStudents(), collectionManager.addFormOfEducation(), collectionManager.addGroupAdmin());
        CollectionManager.group.put(gr.getGroupId(), gr);
    }
}
