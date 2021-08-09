package com.cewb.app.security.service;

import com.cewb.app.model.User;
import com.cewb.app.security.dto.LoginSecurityDto;
import com.cewb.app.security.dto.RegisterSecurityDto;

public interface AuthSecurityService {

    User register(RegisterSecurityDto request);

    User login(LoginSecurityDto request);
}
