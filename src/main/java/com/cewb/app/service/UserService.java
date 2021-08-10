package com.cewb.app.service;

import com.cewb.app.dto.request.UserRequestDto;
import com.cewb.app.model.User;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<User> findAll(int pageNum);

    User findById(Long id);

    User save(UserRequestDto request);

    User update(User user, Long id);

    User delete(Long id);
}
