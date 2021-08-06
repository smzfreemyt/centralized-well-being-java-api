package com.cewb.app.service.impl;

import com.cewb.app.repository.UserRepository;
import com.cewb.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
}
