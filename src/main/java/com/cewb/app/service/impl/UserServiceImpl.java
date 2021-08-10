package com.cewb.app.service.impl;

import com.cewb.app.config.ConfigRepository;
import com.cewb.app.dto.request.UserRequestDto;
import com.cewb.app.dto.response.ResponseMessage;
import com.cewb.app.exception.ExceptionCatcher;
import com.cewb.app.model.Role;
import com.cewb.app.model.User;
import com.cewb.app.repository.UserRepository;
import com.cewb.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

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
    public User save(UserRequestDto request) throws Exception {
        if(this.userRepository.existsByEmail(request.getEmail())) {
            throw new ExceptionCatcher("Email address already exists");
        }
        return this.userRepository.save(this.getStoreData(request));
    }

    public User getStoreData(UserRequestDto request) {
        return new User(
            request.getName(),
            passwordEncoder.encode(request.getPassword()),
            request.getEmail(),
            new Role(request.getRole())
        );
    }

    @Override
    public User update(UserRequestDto request, Long id) {
        User user = this.getStoreData(request);
        user.setId(id);
        return this.userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        User user = this.findById(id);
        this.userRepository.delete(user);
        return ResponseEntity.ok(new ResponseMessage<>(id, "DELETE"));
    }
}
