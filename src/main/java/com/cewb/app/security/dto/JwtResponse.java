package com.cewb.app.security.dto;

import com.cewb.app.model.User;
import com.cewb.app.security.service.UserDetailsServiceImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";

    private UserDetails user;

    public JwtResponse(String accessToken, UserDetails user) {
        this.token = accessToken;
        this.user = user;
    }
}