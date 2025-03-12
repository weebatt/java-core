package org.example.utility;

import org.example.models.StudyGroup;

import java.util.Comparator;

public interface SortMethods {
    Comparator<StudyGroup> sortId();
    Comparator<StudyGroup> sortLocation();
}
