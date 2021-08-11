package com.cewb.app.service.impl;

import com.cewb.app.model.Post;
import com.cewb.app.repository.PostRepository;
import com.cewb.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public Page<Post> findAll(int pageNum) {
        return postRepository.findAll(PageRequest.of(pageNum, 5));
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cant find post with id - " + id));
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post delete(Long id) {
        Post post = findById(id);
        postRepository.delete(post);
        return post;
    }
}
