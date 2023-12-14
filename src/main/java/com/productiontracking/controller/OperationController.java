package com.productiontracking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productiontracking.service.OperationService;

@RestController
@RequestMapping(value = "/api/v1")
public class OperationController {

    private OperationService operationService;

    public OperationController(OperationService operationService) {
        super();
        this.operationService = operationService;
    }
}
