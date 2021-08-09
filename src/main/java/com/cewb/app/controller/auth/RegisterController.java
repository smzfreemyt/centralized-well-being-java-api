package com.cewb.app.controller.auth;

import com.cewb.app.model.User;
import com.cewb.app.security.dto.RegisterSecurityDto;
import com.cewb.app.security.service.AuthSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private AuthSecurityService authSecurityService;

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterSecurityDto user){
        return this.authSecurityService.register(user);
    }
}
