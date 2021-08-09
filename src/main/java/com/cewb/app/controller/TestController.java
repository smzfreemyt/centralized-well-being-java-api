package com.cewb.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    public TestController() {
        this.login();
    }

    public void login(){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken("sam", "test")
            );
            System.out.println("AUTH: NAA");
        } catch (Exception e) {
            System.out.println("AUTH: CANNOT");
        }
    }
}
