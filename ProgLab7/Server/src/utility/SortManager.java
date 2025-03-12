package utility;

import models.StudyGroup;

import java.io.Serializable;
import java.util.Comparator;

public class SortManager implements SortMethods, Serializable {
    public Comparator<StudyGroup> sortId(){
        return new SortGroupId();
    }

    public Comparator<StudyGroup> sortLocation(){
        return new SortLocation();
    }

    public Comparator<StudyGroup> sortExpelledStudents(){
        return new SortExpelledStudents();
    }

    public Comparator<StudyGroup> sortByLotOfParams(){
        return new SortGroupId().thenComparing(new SortGroupName().thenComparing(new SortCoordinates()
                .thenComparing(new SortStudentsCount().thenComparing(new SortExpelledStudents()
                        .thenComparing(new SortTransferredStudents().thenComparing(new SortLocation()))))));
    }

    static class SortGroupId implements Comparator<StudyGroup> {
        @Override
        public int compare(StudyGroup o1, StudyGroup o2) {
            return Long.compare(o1.getGroupId(), o2.getGroupId());
        }
    }

    static class SortGroupName implements  Comparator<StudyGroup>{
        @Override
        public int compare(StudyGroup o1, StudyGroup o2) {
            return CharSequence.compare(o1.getGroupName(), o2.getGroupName());
        }
    }

    static class SortCoordinates implements Comparator<StudyGroup> {
        @Override
        public int compare(StudyGroup o1, StudyGroup o2) {
            return Double.compare((o1.getCoordinates().getX() + o1.getCoordinates().getX()), (o2.getCoordinates().getY() + o2.getCoordinates().getY()));
        }
    }

    static class SortStudentsCount implements Comparator<StudyGroup> {
        @Override
        public int compare(StudyGroup o1, StudyGroup o2) {
            return Integer.compare(o1.getStudentsCount(), o2.getStudentsCount());
        }
    }

    static class SortExpelledStudents implements  Comparator<StudyGroup> {
        @Override
        public int compare(StudyGroup o1, StudyGroup o2) {
            return Long.compare(o1.getExpelledStudents(), o2.getExpelledStudents());
        }
    }

    static class SortTransferredStudents implements  Comparator<StudyGroup> {
        @Override
        public int compare(StudyGroup o1, StudyGroup o2) {
            return Integer.compare(o1.getTransferredStudents(), o2.getTransferredStudents());
        }
    }

    static class SortLocation implements Comparator<StudyGroup> {
        @Override
        public int compare(StudyGroup o1, StudyGroup o2) {
            return Double.compare((o1.getGroupAdmin().getLocation().getX() + o1.getGroupAdmin().getLocation().getY()), (o2.getGroupAdmin().getLocation().getX() + o2.getGroupAdmin().getLocation().getY()));
        }
    }
}
