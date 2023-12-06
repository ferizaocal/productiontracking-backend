package com.productiontracking.service;

import org.springframework.security.authentication.AuthenticationManager;

import com.productiontracking.dto.request.CreateUserDto;
import com.productiontracking.dto.request.LoginDto;
import com.productiontracking.dto.response.LoginResponse;
import com.productiontracking.dto.response.ServiceResponse;
import com.productiontracking.dto.response.UserResponse;
import com.productiontracking.security.TokenProvider;

public interface UserService {
    public ServiceResponse<LoginResponse> forMobilelogin(LoginDto _pUser,
            AuthenticationManager _pAuthenticationManager,
            TokenProvider _pJwtTokenProvider);

    public ServiceResponse<UserResponse> create(CreateUserDto _pUser);

    public Long count();
}
