package com.cewb.app.controller.auth;

import com.cewb.app.model.User;
import com.cewb.app.security.dto.JwtResponse;
import com.cewb.app.security.dto.LoginSecurityDto;
import com.cewb.app.security.jwt.JwtProvider;
import com.cewb.app.security.service.AuthSecurityService;
import com.cewb.app.security.service.UserDetailsServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Log4j2
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private AuthSecurityService authSecurityService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    /**
     * Only user can log in
     */
    @PostMapping("/api/login")
    @PostAuthorize("hasAnyAuthority(@R.ROLE_USER)")
    public ResponseEntity<?> userLogin(@Valid @RequestBody LoginSecurityDto loginRequest) {
        log.info("Login as USER role");
        return this.doLogin(loginRequest);
    }

    /**
     * Only admin can log in
     */
    @PostMapping("/api/admin/login")
    @PostAuthorize("hasAnyAuthority('ADMIN','EDITOR')")
    public ResponseEntity<?> adminLogin(@Valid @RequestBody LoginSecurityDto loginRequest) {
        log.info("Login as ADMIN role");
        return this.doLogin(loginRequest);
    }

    private ResponseEntity<?> doLogin(LoginSecurityDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails));
    }
}
