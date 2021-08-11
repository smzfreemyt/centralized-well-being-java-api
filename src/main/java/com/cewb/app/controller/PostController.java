package com.cewb.app.controller;

import com.cewb.app.model.Post;
import com.cewb.app.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PostController {
	
	private static final Logger log = LoggerFactory.getLogger(PostController.class);

	@Autowired
	PostService postService;

	//Create post
	@PostMapping("/posts")
	public Post createPost(@RequestBody Post post) {
		log.info("Create post endpoint");
		return postService.save(post);
	}

	//View posts
	@GetMapping("/posts")
	public Page<Post> readPosts(@RequestParam(value = "page", defaultValue = "0") int pageNum){
		log.info("Read posts by page endpoint");
		return postService.findAll(pageNum);
	}

	//View post
	@GetMapping("/posts/{id}")
	public Post readPost(@PathVariable Long id) {
		log.info("Read post with id " + id);
		return postService.findById(id);
	}

	//Update post
	@PutMapping("/posts")
	public Post updatePost(@RequestBody Post post) {
		log.info("Update company with id " + post.getId());
		postService.save(post);
		return post;
	}

	//Delete post
	@DeleteMapping("/posts/{id}")
	public Post deletePost(@PathVariable Long id) {
		log.info("Delete post with id " + id);
		return postService.delete(id);
	}
}
