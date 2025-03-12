package commands;

import models.Command;
import models.StudyGroup;
import utility.CollectionManager;
import utility.FileManager;
import utility.SortManager;

import java.util.*;

/**
 * Команда 'remove_greater_key'. Удаляет из коллекции все элементы, ключ которых превышает заданный.
 * @author butareyka
 */
public class RemoveGreaterKey extends Command {
    public RemoveGreaterKey(){
        super("remove_greater_key", "удалить из коллекции все элементы, ключ которых превышает заданный");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String value) {
        Long key = Long.parseLong(value);

        new FileManager().convertStringToCollection();
        Comparator<StudyGroup> groupComparator1 = new SortManager().sortExpelledStudents();
        List<StudyGroup> sortedGroup = new ArrayList<>(CollectionManager.group.values());
        List<StudyGroup> sortedFileGroup = new ArrayList<>(FileManager.fileGroup.values());

        Collections.sort(sortedGroup, groupComparator1);
        Collections.sort(sortedFileGroup, groupComparator1);

        Iterator<StudyGroup> iterator1 = sortedGroup.iterator();
        Iterator<StudyGroup> iterator2 = sortedFileGroup.iterator();

        while (iterator1.hasNext()) {
            StudyGroup item = iterator1.next();
            Long keyGroup = item.getGroupId();
            int result = keyGroup.compareTo(key);

            if (result > 0) {
                iterator1.remove();
            }
        }

        while (iterator2.hasNext()) {
            StudyGroup item = iterator2.next();
            Long keyFileGroup = item.getGroupId();
            int result = keyFileGroup.compareTo(key);

            if (result > 0) {
                iterator2.remove();
            }
        }

        Comparator<StudyGroup> groupComparator2 = new SortManager().sortId();
        Collections.sort(sortedGroup, groupComparator2);
        Collections.sort(sortedFileGroup, groupComparator2);

        System.out.println("Элементы коллекции: ");
        for (StudyGroup item : sortedGroup) {
            System.out.println("{" + item.getGroupId() + "=" + item + "}");
        }
        System.out.println("Сохраненные элементы коллекции: ");
        for (StudyGroup item : sortedFileGroup) {
            System.out.println("{" + item.getGroupId() + "=" + item + "}");
        }
    }
}
