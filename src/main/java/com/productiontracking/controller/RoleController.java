package com.productiontracking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productiontracking.service.RoleService;

@RestController
@RequestMapping(value = "/api/v1")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        super();
        this.roleService = roleService;
    }
}
