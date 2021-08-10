package com.cewb.app.service.impl;

import com.cewb.app.config.ConfigRepository;
import com.cewb.app.dto.request.UserRequestDto;
import com.cewb.app.dto.response.ResponseMessage;
import com.cewb.app.model.Role;
import com.cewb.app.model.User;
import com.cewb.app.repository.UserRepository;
import com.cewb.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(int pageNum) {
        return this.userRepository.findAll(PageRequest.of(pageNum, ConfigRepository.PER_PAGE));
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find User id."));
    }

    @Override
    public User save(UserRequestDto request) {
        return this.userRepository.save(new User(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                new Role(request.getRole())
        ));
    }

    @Override
    public User update(UserRequestDto user, Long id) {
        this.findById(id);
        return this.save(user);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        User user = this.findById(id);
        this.userRepository.delete(user);
        return ResponseEntity.ok(new ResponseMessage<>(id, "DELETE"));
    }
}
