package com.cewb.app.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.cewb.app.model.User;
import com.cewb.app.service.UserService;

@Component
public class SecurityAuditorAware implements AuditorAware<User> {
	
	@Autowired
	UserService userService;
	
	@Override
	public Optional<User> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        User user = userService.findById(((UserPrinciple) authentication.getPrincipal()).getId());
        
        return Optional.ofNullable(user);
	}

}
