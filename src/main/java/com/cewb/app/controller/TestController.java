package com.cewb.app.controller;

import com.cewb.app.security.dto.JwtResponse;
import com.cewb.app.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class TestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;


    public TestController() {
        this.login();
    }


    public ResponseEntity<?> login() {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("sam", "test", null)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
