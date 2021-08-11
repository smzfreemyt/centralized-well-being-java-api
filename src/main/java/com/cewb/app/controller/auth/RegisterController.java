package com.cewb.app.controller.auth;

import com.cewb.app.exception.ExceptionCatcher;
import com.cewb.app.model.User;
import com.cewb.app.security.dto.RegisterResponse;
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
    public RegisterResponse register(@Valid @RequestBody RegisterSecurityDto user) throws Exception {
        return this.authSecurityService.register(user);
    }
}
