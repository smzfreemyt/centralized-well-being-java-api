package com.cewb.app.security.service;

import com.cewb.app.model.Role;
import com.cewb.app.model.User;
import com.cewb.app.repository.UserRepository;
import com.cewb.app.security.dto.UserSecurityDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthSecurityServiceImpl implements AuthSecurityService{

    private UserRepository userRepository;

    @Override
    public User register(UserSecurityDto request) {
        System.out.println("EMAIL: " + request.getEmail());
        Role role = new Role();
        role.setId(2L);

        User user = new User();
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRole(role);
        return this.userRepository.save(user);
    }
}
