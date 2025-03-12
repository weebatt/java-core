package org.example.models;

import java.io.Serial;
import java.io.Serializable;

public enum EyesColor implements Serializable {
    RED,
    BLACK,
    BLUE,
    WHITE;

    @Serial
    private static final long serialVersionUID = 6L;
}
