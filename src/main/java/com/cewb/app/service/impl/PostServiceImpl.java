package com.cewb.app.service.impl;

import com.cewb.app.model.Category;
import com.cewb.app.model.Post;
import com.cewb.app.repository.PostRepository;
import com.cewb.app.service.CategoryService;
import com.cewb.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryService categoryService;

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
        Category cat = categoryService.findById(post.getCategory_id());
        post.setCategory(cat);
        return postRepository.save(post);
    }

    @Override
    public Post delete(Long id) {
        Post post = findById(id);
        postRepository.delete(post);
        return post;
    }

    @Override
    public List<Post> findByCategory(long categoryId) {
        List<Post> postList = new ArrayList<>();
        for(Post post : findAll()){
            if(post.getCategory().getId() == categoryId){
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
