package com.cewb.app.controller;

import com.cewb.app.config.ConfigRole;
import com.cewb.app.dto.request.UserRequestDto;
import com.cewb.app.model.Category;
import com.cewb.app.model.User;
import com.cewb.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAnyAuthority(@R.ROLE_ADMIN)")
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

    @GetMapping("/search")
    public Page<User> readCategoriesInPage(@RequestParam(value = "page", defaultValue = "0") int pageNum, @RequestParam(defaultValue = "", value = "search") String search) {
        return userService.findByKeyword(pageNum, search);
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

