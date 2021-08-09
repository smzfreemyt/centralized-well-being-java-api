package com.cewb.app.controller.auth;

import com.cewb.app.model.User;
import com.cewb.app.security.dto.LoginSecurityDto;
import com.cewb.app.security.dto.RegisterSecurityDto;
import com.cewb.app.security.service.AuthSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    private AuthSecurityService authSecurityService;

    @PostMapping("/api/admin/login")
    public User register(@Valid @RequestBody LoginSecurityDto user){
        return this.authSecurityService.login(user);
    }
}
