package com.cewb.app.repository;

import com.cewb.app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query("from User u where u.name like ?1")
    Page<User> findByNameLike(String sqlKeyword, PageRequest of);

//    Optional<User> findByUsername(String username);
}