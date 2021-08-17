package com.cewb.app.security.service;

import com.cewb.app.model.User;
import com.cewb.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
    	
        User user = userRepository.findByEmail(email)
                	.orElseThrow(() -> 
                        new UsernameNotFoundException("Email address not found")
        );

        return UserPrinciple.build(user);
    }
}