package commands;

import models.Command;
import models.StudyGroup;
import utility.CollectionManager;
import utility.FileManager;
import utility.SortManager;

import java.io.File;
import java.util.*;

/**
 * Команда 'show'. Выводит в стандартный поток вывода все элементы коллекции в строковом представлении.
 * @author butareyka
 */
public class Show extends Command{
    public Show() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String value) {
        FileManager fileManager = new FileManager();
        Comparator<StudyGroup> groupComparator = new SortManager().sortId();

        File file = new File(System.getenv("MYFILE"));

        if (file.length() == 0 && CollectionManager.group.isEmpty()) {
            System.out.println("Коллекция пуста!");
        }
        if (file.length() != 0 ){
            System.out.println("Имеющиеся, сохраненные элементы коллекции: ");
            fileManager.convertStringToCollection();
            List<StudyGroup> sortedFileGroup =  new ArrayList<>(FileManager.fileGroup.values());
            Collections.sort(sortedFileGroup, groupComparator);
            for (StudyGroup item : sortedFileGroup) {
                System.out.println("{" + item.getGroupId()  + "=" + item + "}");
            }
        }
        if (!CollectionManager.group.isEmpty()){
            System.out.println("Имеющиеся элементы коллекции: ");
            List<StudyGroup> sortedGroup = new ArrayList<>(CollectionManager.group.values());
            Collections.sort(sortedGroup, groupComparator);
            for (StudyGroup item : sortedGroup) {
                System.out.println("{" + item.getGroupId()  + "=" + item + "}");
            }
        }
    }
}

