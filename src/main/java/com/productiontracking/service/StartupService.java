package com.productiontracking.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.productiontracking.dto.request.CreateUserRequest;
import com.productiontracking.entity.Role;
import com.productiontracking.repository.RoleRepository;

@Service
public class StartupService {
    Logger logger = org.slf4j.LoggerFactory.getLogger(StartupService.class);

    RoleRepository roleRepository;
    UserService userService;

    public StartupService(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {

        initRole();
        initUser();
    }

    public void initUser() {
        Long count = userService.count();
        if (count == 0) {
            logger.info("No user found, creating default user");
            userService.create(
                    new CreateUserRequest("Özkan", "Kocakaplan", "ozkankocakaplan07@gmail.com", "+905313179397",
                            "admin",
                            "Admin"));
            logger.info("Default user created");
        }
    }

    public void initRole() {
        Long count = roleRepository.count();
        if (count == 0) {
            logger.info("No role found, creating default role");
            roleRepository.save(new Role("Admin"));
            logger.info("Default role created");

        }
    }

}
