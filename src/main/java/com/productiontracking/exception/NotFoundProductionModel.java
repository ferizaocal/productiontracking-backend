package com.productiontracking.exception;

public class NotFoundProductionModel extends RuntimeException {
    public NotFoundProductionModel(String message) {
        super(message);
    }

    public NotFoundProductionModel(Long id) {
        super("Production Model not found: " + id);
    }
}
