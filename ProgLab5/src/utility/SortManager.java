package utility;

import models.StudyGroup;

import java.util.Comparator;

/**
 * Класс отвечающий за сортировку коллекции.
 * @author butareyka
 */
public class SortManager implements SortMethods{
    public Comparator<StudyGroup> sortId(){
        return new SortGroupId();
    }

    public Comparator<StudyGroup> sortExpelledStudents(){
        return new SortExpelledStudents();
    }

    static class SortExpelledStudents implements Comparator<StudyGroup>{
        @Override
        public int compare(StudyGroup o1, StudyGroup o2) {
            return Long.compare(o1.getExpelledStudents(), o2.getExpelledStudents());
        }
    }
    static class SortGroupId implements Comparator<StudyGroup> {
        @Override
        public int compare(StudyGroup o1, StudyGroup o2) {
            return Long.compare(o1.getGroupId(), o2.getGroupId());
        }
    }
}
