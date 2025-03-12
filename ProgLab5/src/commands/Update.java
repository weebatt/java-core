package commands;

import models.Command;
import models.StudyGroup;
import utility.CollectionManager;

import java.util.Scanner;

/**
 * Команда 'update'. Обновляет значение элемента коллекции, group_id которого равен заданному.
 * @author butareyka
 */
public class Update extends Command {
    public Update(){
        super("update", "обновить значение элемента коллекции, group_id которого равен заданному");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String value) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите group_id, значения элемента которого вы хотите изменить: ");
        Long key = scanner.nextLong();
        if (CollectionManager.group.containsKey(key)){
            System.out.println("Вы можете обновить значение элемента коллекции group_id, которого равен " + key);
            CollectionManager collectionManager = new CollectionManager();
            StudyGroup gr = new StudyGroup(key, collectionManager.addGroupName(), collectionManager.addCreationDate(), collectionManager.addCoordinates(), collectionManager.addStudentsCount(), collectionManager.addExpelledStudents(), collectionManager.addTransferredStudents(), collectionManager.addFormOfEducation(), collectionManager.addGroupAdmin());
            CollectionManager.group.put(key, gr);
            new Save().executionResponse("");
        } else {
            System.out.println("Заданного group_id не существует!");
        }
    }
}
