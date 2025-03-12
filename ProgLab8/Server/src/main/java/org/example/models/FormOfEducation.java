package org.example.models;

import java.io.Serial;
import java.io.Serializable;

public enum FormOfEducation implements Serializable {
    DISTANCE_EDUCATION,
    FULL_TIME_EDUCATION,
    EVENING_CLASSES;
    @Serial
    private static final long serialVersionUID = 5L;
}