package com.cewb.app.controller.auth;

import com.cewb.app.model.User;
import com.cewb.app.security.dto.UserSecurityDto;
import com.cewb.app.security.service.AuthSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterController {

    private AuthSecurityService authSecurityService;

    @PostMapping("/register")
    public User register(@RequestBody UserSecurityDto user){
        return this.authSecurityService.register(user);
    }
}
