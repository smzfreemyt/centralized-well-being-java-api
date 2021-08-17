package com.cewb.app.controller;

import com.cewb.app.dto.request.UserRequestDto;
import com.cewb.app.model.User;
import com.cewb.app.security.jwt.JwtAuthTokenFilter;
import com.cewb.app.security.jwt.JwtProvider;
import com.cewb.app.security.service.UserDetailsServiceImpl;
import com.cewb.app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Log4j2
public class ProfileController {

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private JwtAuthTokenFilter jwtAuthTokenFilter;

    @GetMapping("api/me")
    public UserDetails profile() {
        return (UserDetails) SecurityContextHolder
                .getContext().getAuthentication()
                .getPrincipal();
    }
}
