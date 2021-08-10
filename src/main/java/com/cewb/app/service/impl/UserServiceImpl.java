package com.cewb.app.service.impl;

import com.cewb.app.model.User;
import com.cewb.app.repository.UserRepository;
import com.cewb.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(int pageNum) {
        return this.userRepository.findAll(PageRequest.of(pageNum, 2));
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find User id."));
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user, Long id) {
        return null;
    }

    @Override
    public User delete(Long id) {
        return null;
    }
}
