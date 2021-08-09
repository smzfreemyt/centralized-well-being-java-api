package com.cewb.app.controller.auth;

import com.cewb.app.model.User;
import com.cewb.app.security.dto.AuthSecurityDto;
import com.cewb.app.security.service.AuthSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private AuthSecurityService authSecurityService;

    @PostMapping("/register")
    public User register(@Validated @RequestBody AuthSecurityDto user){
        return this.authSecurityService.register(user);
    }
}
