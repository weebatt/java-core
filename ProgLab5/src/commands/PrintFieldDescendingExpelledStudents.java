package commands;

import models.Command;
import models.StudyGroup;
import utility.CollectionManager;
import utility.FileManager;
import utility.SortManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Команда 'print_field_descending_expelled_students'. Выводит значения поля expelledStudents всех элементов в порядке убывания.
 * @author butareyka
 */
public class PrintFieldDescendingExpelledStudents extends Command {
    public PrintFieldDescendingExpelledStudents(){
        super("print_field_descending_expelled_students", "вывести значения поля expelledStudents всех элементов в порядке убывания");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String value) {
        Comparator<StudyGroup> groupComparator = new SortManager().sortExpelledStudents();
        List<StudyGroup> sortedGroup = new ArrayList<>(CollectionManager.group.values());
        List<StudyGroup> sortedFileGroup = new ArrayList<>(FileManager.fileGroup.values());

        Collections.sort(sortedGroup, groupComparator);
        Collections.sort(sortedFileGroup, groupComparator);

        System.out.println("Элементы коллекции отсортированные по полю expelledStudents: ");
        for (StudyGroup item : sortedGroup) {
            System.out.println("{" + item.getGroupId() + "=" + item + "}");
        }
        System.out.println("Сохраненные элементы коллекции отсортированные по полю expelledStudents: ");
        for (StudyGroup item : sortedFileGroup) {
            System.out.println("{" + item.getGroupId() + "=" + item + "}");
        }
    }
}
