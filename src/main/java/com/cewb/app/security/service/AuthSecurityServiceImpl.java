package com.cewb.app.security.service;

import com.cewb.app.config.ConfigRole;
import com.cewb.app.exception.ExceptionCatcher;
import com.cewb.app.model.Role;
import com.cewb.app.model.User;
import com.cewb.app.repository.UserRepository;
import com.cewb.app.security.dto.AuthSecurityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthSecurityServiceImpl implements AuthSecurityService{

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthSecurityServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(AuthSecurityDto request) throws ExceptionCatcher {
        if(this.userRepository.existsByEmail(request.getEmail())) {
            throw new ExceptionCatcher("Email address already exists");
        }
        return this.userRepository.save(new User(
                request.getName(),
                passwordEncoder.encode(request.getPassword()),
                request.getEmail(),
                new Role(ConfigRole.ROLE_USER))
        );
    }
}
