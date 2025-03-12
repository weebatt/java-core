package org.example.models;

import java.io.Serial;
import java.io.Serializable;

public enum Country implements Serializable {
    GERMANY,
    SPAIN,
    INDIA,
    THAILAND;

    @Serial
    private static final long serialVersionUID = 7L;
}
