package com.productiontracking.exception;

public class DuplicateProductionModel extends RuntimeException {

    public DuplicateProductionModel(String message) {
        super("Duplicate Production Model: " + message);
    }
}
