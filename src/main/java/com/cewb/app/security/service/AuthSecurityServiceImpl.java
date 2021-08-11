package com.cewb.app.security.service;

import com.cewb.app.config.Config;
import com.cewb.app.model.Role;
import com.cewb.app.model.User;
import com.cewb.app.repository.UserRepository;
import com.cewb.app.security.dto.LoginSecurityDto;
import com.cewb.app.security.dto.RegisterSecurityDto;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthSecurityServiceImpl implements AuthSecurityService{

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthSecurityServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User register(RegisterSecurityDto request) {
        User user = new User(
                request.getName(),
                passwordEncoder.encode(request.getPassword()),
                request.getEmail());

        Role role = new Role(Config.ROLE_USER_TEXT);
        user.getRoles().add(role);

        role.getUsers().add(user);

        return this.userRepository.save(user);
    }

    @Override
    public User login(LoginSecurityDto request) {
        System.out.println("LOGIN");
        return null;
    }
}
