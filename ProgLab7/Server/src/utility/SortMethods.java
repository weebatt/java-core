package utility;

import models.StudyGroup;

import java.util.Comparator;

public interface SortMethods {
    Comparator<StudyGroup> sortId();
    Comparator<StudyGroup> sortLocation();
}
