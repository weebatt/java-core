package org.example.models;

import java.io.Serial;
import java.io.Serializable;

public enum HairColor implements Serializable {
    RED,
    BLACK,
    BLUE,
    BROWN;

    @Serial
    private static final long serialVersionUID = 4L;
}
