package com.cewb.app.security.service;

import com.cewb.app.model.User;
import com.cewb.app.security.dto.UserSecurityDto;

public interface AuthSecurityService {

    User register(UserSecurityDto request);
}
