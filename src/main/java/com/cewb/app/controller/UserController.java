package com.cewb.app.controller;

import com.cewb.app.model.Company;
import com.cewb.app.model.User;
import com.cewb.app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping(name = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> users(@RequestParam(value = "page", defaultValue = "0") int pageNum) {
        return this.userService.findAll(pageNum);
    }

    @PostMapping
    public User create(@RequestBody User request) {
        return this.userService.save(request);
    }

    @GetMapping("/{id}")
    public User item(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User request, @PathVariable("id") Long id) {
        return this.userService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable Long id) {
        return this.userService.delete(id);
    }
}
