package utility;

import models.StudyGroup;

import java.util.Comparator;

/**
 * Интерфейс хранящий методы SortManager.
 * @author butareyka
 */
public interface SortMethods {
    Comparator<StudyGroup> sortId();
    Comparator<StudyGroup> sortExpelledStudents();
}
