package com.cewb.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PostController {
	
	private static final Logger log = LoggerFactory.getLogger(PostController.class);
	
	//Sample rest endpoint
	@GetMapping("/hello")
	public String helloworld() {
		log.info("/hello endpoint");
		
		return "Hello world";
	}
}
