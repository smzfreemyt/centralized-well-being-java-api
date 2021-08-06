package com.cewb.app.security.service;

import com.cewb.app.config.UserRole;
import com.cewb.app.model.Role;
import com.cewb.app.model.User;
import com.cewb.app.repository.UserRepository;
import com.cewb.app.security.dto.AuthSecurityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthSecurityServiceImpl implements AuthSecurityService{

    // transfer this to constats later
    private static final Long  ROLE_USER = 2L;
    private static final Long  ROLE_ADMIN = 1L;

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthSecurityServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(AuthSecurityDto request) {
        return this.userRepository.save(new User(
                request.getName(),
                passwordEncoder.encode(request.getPassword()),
                request.getEmail(),
                new Role(ROLE_USER))
        );
    }
}
