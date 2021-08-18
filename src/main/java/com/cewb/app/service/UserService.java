package com.cewb.app.service;

import com.cewb.app.dto.request.UserRequestDto;
import com.cewb.app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface UserService {

    Page<User> findAll(int pageNum);

    User findById(Long id);

    User save(UserRequestDto request) throws Exception;

    User update(UserRequestDto user, Long id);

    ResponseEntity<?> delete(Long id);

    Page<User> findByKeyword(int pageNum, String search);
}
