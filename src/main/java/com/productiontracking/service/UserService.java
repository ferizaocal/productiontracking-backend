package com.productiontracking.service;

import org.springframework.security.authentication.AuthenticationManager;

import com.productiontracking.dto.request.CreateUserRequest;
import com.productiontracking.dto.request.LoginRequest;
import com.productiontracking.dto.response.LoginResponse;
import com.productiontracking.dto.response.ServiceResponse;
import com.productiontracking.dto.response.UserResponse;
import com.productiontracking.security.TokenProvider;

public interface UserService {
    public ServiceResponse<LoginResponse> forMobilelogin(LoginRequest _pUser,
            AuthenticationManager _pAuthenticationManager,
            TokenProvider _pJwtTokenProvider);

    public ServiceResponse<UserResponse> create(CreateUserRequest _pUser);

    public ServiceResponse<UserResponse> getFindAllByUserRole(Long roleId, Long getByUserId);

    public ServiceResponse<UserResponse> updateActiveProductionModelId(Long _pId, Long _pProductionModelId);

    public Long count();
}
