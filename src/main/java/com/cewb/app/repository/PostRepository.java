package com.cewb.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cewb.app.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
