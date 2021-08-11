package com.cewb.app.security.service;

import com.cewb.app.model.User;
import com.cewb.app.security.dto.LoginSecurityDto;
import com.cewb.app.security.dto.RegisterResponse;
import com.cewb.app.security.dto.RegisterSecurityDto;

import java.util.Optional;

public interface AuthSecurityService {

    RegisterResponse register(RegisterSecurityDto request) throws Exception;

    User login(LoginSecurityDto request);
}
