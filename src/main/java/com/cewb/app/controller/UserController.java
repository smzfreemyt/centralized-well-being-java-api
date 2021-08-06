package com.cewb.app.controller;

import com.cewb.app.model.User;
import com.cewb.app.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String hello() {
        return "Hello world!";
    }

    @GetMapping("register")
    public String register() {
        return "Registration";
    }

/*    @PreAuthorize("isAnonymous() && #text.length() < 6")
    @GetMapping("/message/{text}")
    public String hello(@PathVariable String text) {
        return "Hello anonymus user!";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @PreAuthorize("isAuthenticated() && @securityAuthorizationService.isCurrentUser(#username)")
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityExistsException("User " + username + " doesn't exist in database"));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current")
    public User getUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityExistsException("Wrong username " + username + " in SecurityContexHolder"));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/token")
    public String checkRefreshToken() {
        return "Compare old and new token";
    }
 */
}