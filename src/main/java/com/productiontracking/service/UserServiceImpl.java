package com.productiontracking.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.productiontracking.dto.request.CreateUserDto;
import com.productiontracking.dto.request.LoginDto;
import com.productiontracking.dto.response.LoginResponse;
import com.productiontracking.dto.response.ServiceResponse;
import com.productiontracking.dto.response.UserResponse;
import com.productiontracking.entity.Role;
import com.productiontracking.entity.User;
import com.productiontracking.mapper.ModelMapperService;
import com.productiontracking.repository.RoleRepository;
import com.productiontracking.repository.UserRepository;
import com.productiontracking.security.TokenProvider;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
    private ModelMapperService _modelMapperService;

    private UserRepository _userRepository;
    private PasswordEncoder _passwordEncoder;
    private RoleRepository _roleRepository;

    public UserServiceImpl(UserRepository _pUserRepository, ModelMapperService _pModelMapperService,
            PasswordEncoder _pPasswordEncoder, RoleRepository _pRoleRepository) {
        _userRepository = _pUserRepository;
        _modelMapperService = _pModelMapperService;
        _passwordEncoder = _pPasswordEncoder;
        _roleRepository = _pRoleRepository;
    }

    @Override
    public ServiceResponse<LoginResponse> forMobilelogin(LoginDto _pUser,
            AuthenticationManager _pAuthenticationManager,
            TokenProvider _pJwtTokenProvider) {
        ServiceResponse<LoginResponse> _vResponse = new ServiceResponse<LoginResponse>();

        UsernamePasswordAuthenticationToken _vAuthenticationToken = new UsernamePasswordAuthenticationToken(
                _pUser.getEmail(), _pUser.getPassword());
        Authentication _vAuthentication = _pAuthenticationManager.authenticate(_vAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(_vAuthentication);
        String _vJwt = _pJwtTokenProvider.generateToken(_vAuthentication);
        User _vUser = _userRepository.findByEmail(_pUser.getEmail());
        LoginResponse _vLoginResponse = _modelMapperService.forResponse().map(_vUser, LoginResponse.class);
        _vLoginResponse.setToken(_vJwt);
        _vResponse.setEntity(_vLoginResponse)
                .setIsSuccessful(true);
        return _vResponse;
    }

    @Override
    public ServiceResponse<UserResponse> create(CreateUserDto _pUser) {
        ServiceResponse<UserResponse> _vResponse = new ServiceResponse<UserResponse>();
        try {
            User _vUser = _modelMapperService.forRequest().map(_pUser, User.class);
            Set<Role> _vRoles = new HashSet<>();
            Role _vRole = _roleRepository.findByName(_pUser.getRole());
            _vRoles.add(_vRole);
            _vUser.setPassword(_passwordEncoder.encode(_pUser.getPassword()));
            _vUser.setRoles(_vRoles);
            _vUser = _userRepository.save(_vUser);
            UserResponse _vUserResponse = _modelMapperService.forResponse().map(_vUser, UserResponse.class);
            _vResponse.setEntity(_vUserResponse)
                    .setIsSuccessful(true);
            logger.info("User created: " + _vUser.getEmail() + " with role: " + _pUser.getRole());
        } catch (Exception e) {
            logger.error(e.getMessage());
            _vResponse.setExceptionMessage(e.getMessage())
                    .setHasExceptionError(true);
        }
        return _vResponse;
    }

    @Override
    public Long count() {
        return _userRepository.count();
    }
}
