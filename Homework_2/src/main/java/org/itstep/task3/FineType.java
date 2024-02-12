package org.itstep.task3;

import java.util.Arrays;
import java.util.Optional;

public enum FineType {
    CIVIL(0),
    AUTO(1),
    COURT(2);

    private final int value;

    FineType(int value) {
        this.value = value;
    }

    public static Optional<FineType> valueOf(int value){
        return Arrays.stream(values()).filter(type -> type.value == value).findFirst();
    }
}
