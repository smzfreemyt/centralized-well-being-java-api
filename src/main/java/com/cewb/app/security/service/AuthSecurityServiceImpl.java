package com.cewb.app.security.service;

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

    private PasswordEncoder passwordEncoder;

    public AuthSecurityServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(AuthSecurityDto request) {
        Role role = new Role();
        role.setId(2L);

        User user = new User();
        user.setName(request.getName());
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(role);
        return this.userRepository.save(user);
    }
}
