package com.cewb.app.security.service;

import com.cewb.app.exception.ExceptionCatcher;
import com.cewb.app.model.User;
import com.cewb.app.security.dto.AuthSecurityDto;

public interface AuthSecurityService {

    User register(AuthSecurityDto request) throws ExceptionCatcher;
}
