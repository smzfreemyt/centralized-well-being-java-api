package com.cewb.app.security.service;

import com.cewb.app.config.ConfigRole;
import com.cewb.app.exception.ExceptionCatcher;
import com.cewb.app.model.Role;
import com.cewb.app.model.User;
import com.cewb.app.repository.UserRepository;
import com.cewb.app.security.dto.LoginSecurityDto;
import com.cewb.app.security.dto.RegisterResponse;
import com.cewb.app.security.dto.RegisterSecurityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthSecurityServiceImpl implements AuthSecurityService{

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthSecurityServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponse register(RegisterSecurityDto request) throws Exception {
        if(this.userRepository.existsByEmail(request.getEmail())) {
            throw new ExceptionCatcher("Email address already exists");
        }
        try {
            User user = new User(
                    request.getName(),
                    passwordEncoder.encode(request.getPassword()),
                    request.getEmail());
            Role role = new Role(ConfigRole.ROLE_USER);
            user.getRoles().add(role);
            role.getUsers().add(user);
            this.userRepository.save(user);
            return new RegisterResponse(user.getId(), user.getEmail(), user.getName());
        } catch (Exception e) {
            throw new Exception("There is something wrong while signing up!");
        }
    }

    @Override
    public User login(LoginSecurityDto request) {
        System.out.println("LOGIN");
        return null;
    }
}
