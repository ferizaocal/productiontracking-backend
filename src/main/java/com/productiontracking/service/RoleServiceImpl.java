package com.productiontracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productiontracking.dto.request.CreateRoleRequest;
import com.productiontracking.dto.request.UpdateRoleRequest;
import com.productiontracking.dto.response.RoleResponse;
import com.productiontracking.dto.response.ServiceResponse;
import com.productiontracking.entity.Role;
import com.productiontracking.entity.RoleOperation;
import com.productiontracking.mapper.ModelMapperService;
import com.productiontracking.repository.RoleOperationRepository;
import com.productiontracking.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    ModelMapperService modelMapperService;

    private RoleRepository roleRepository;
    private RoleOperationRepository roleOperationRepository;

    public RoleServiceImpl(RoleRepository roleRepository, RoleOperationRepository roleOperationRepository) {
        super();
        this.roleRepository = roleRepository;
        this.roleOperationRepository = roleOperationRepository;
    }

    @Override
    public ServiceResponse<RoleResponse> createRole(CreateRoleRequest request) {
        ServiceResponse<RoleResponse> response = new ServiceResponse<>();
        try {
            Role role = modelMapperService.forRequest().map(request, Role.class);
            role = roleRepository.save(role);
            RoleOperation roleOperation = new RoleOperation(role.getId(), request.getOperationId());
            roleOperationRepository.save(roleOperation);
            RoleResponse roleResponse = modelMapperService.forResponse().map(role, RoleResponse.class);
            response.setEntity(roleResponse).setIsSuccessful(true);
        } catch (Exception e) {
            log.error("Error while creating role", e);
            response.setHasExceptionError(true).setExceptionMessage("Error while creating role " + e.getMessage());
        }
        return response;
    }

    @Override
    public ServiceResponse<RoleResponse> updateRole(UpdateRoleRequest request) {
        ServiceResponse<RoleResponse> response = new ServiceResponse<>();
        try {
            Role role = modelMapperService.forRequest().map(request, Role.class);
            role = roleRepository.save(role);
            RoleOperation roleOperation = new RoleOperation(role.getId(), request.getOperationId());
            roleOperationRepository.save(roleOperation);
            RoleResponse roleResponse = modelMapperService.forResponse().map(role, RoleResponse.class);
            response.setEntity(roleResponse).setIsSuccessful(true);
        } catch (Exception e) {
            log.error("Error while updating role", e);
            response.setHasExceptionError(true).setExceptionMessage("Error while updating role " + e.getMessage());
        }
        return response;
    }

    @Override
    public ServiceResponse<RoleResponse> deleteRole(Long id) {
        ServiceResponse<RoleResponse> response = new ServiceResponse<>();
        try {
            Role role = roleRepository.findById(id).get();
            role.setIsDeleted(true);
            roleRepository.save(role);
            RoleOperation roleOperation = roleOperationRepository.findByRoleId(id);
            roleOperation.setIsDeleted(true);
            roleOperationRepository.save(roleOperation);
            response.setIsSuccessful(true);
        } catch (Exception e) {
            log.error("Error while deleting role", e);
            response.setHasExceptionError(true).setExceptionMessage("Error while deleting role " + e.getMessage());
        }
        return response;
    }

    @Override
    public ServiceResponse<RoleResponse> getRole(Long id) {
        ServiceResponse<RoleResponse> response = new ServiceResponse<>();
        try {
            Role role = roleRepository.findById(id).get();
            RoleResponse roleResponse = modelMapperService.forResponse().map(role, RoleResponse.class);
            if (role.getRoleOperation() != null) {
                roleResponse.setOperationName(role.getRoleOperation().getOperationName());
            }
            response.setEntity(roleResponse).setIsSuccessful(true);
        } catch (Exception e) {
            log.error("Error while getting role", e);
            response.setHasExceptionError(true).setExceptionMessage("Error while getting role " + e.getMessage());
        }
        return response;
    }

    @Override
    public ServiceResponse<RoleResponse> findAll() {
        ServiceResponse<RoleResponse> response = new ServiceResponse<>();
        try {
            List<Role> roles = roleRepository.findAll();
            List<RoleResponse> roleResponses = roles.stream().map(role -> {
                RoleResponse roleResponse = modelMapperService.forResponse().map(role, RoleResponse.class);
                if (role.getRoleOperation() != null) {
                    roleResponse.setOperationName(role.getRoleOperation().getOperationName());
                }
                return roleResponse;
            }).collect(java.util.stream.Collectors.toList());
            response.setList(roleResponses).setIsSuccessful(true);
        } catch (Exception e) {
            log.error("Error while getting all roles", e);
            response.setHasExceptionError(true).setExceptionMessage("Error while getting all roles " + e.getMessage());
        }
        return response;
    }
}
