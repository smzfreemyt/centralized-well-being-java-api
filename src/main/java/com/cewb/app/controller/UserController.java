package com.cewb.app.controller;

import com.cewb.app.dto.request.UserRequestDto;
import com.cewb.app.model.User;
import com.cewb.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> users(@RequestParam(value = "page", defaultValue = "0") int pageNum) {
        return this.userService.findAll(pageNum);
    }

    @PostMapping
    public User create(@Valid @RequestBody UserRequestDto request) throws Exception {
        return this.userService.save(request);
    }

    @GetMapping("/{id}")
    public User item(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @PutMapping("/{id}")
    public User update(@Valid @RequestBody UserRequestDto request, @PathVariable("id") Long id) {
        return this.userService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.userService.delete(id);
    }
}
