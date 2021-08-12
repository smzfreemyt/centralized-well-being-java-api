package com.cewb.app.controller;

import com.cewb.app.model.Post;
import com.cewb.app.repository.RoleRepository;
import com.cewb.app.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {PostController.class, PostService.class})
@WebMvcTest
class PostControllerTest {

    @Autowired
    PostController postController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostService postService;

    @Test
    public void getPostById() throws Exception {

        // Mock the data returned by the service
        Post post = new Post();
        post.setTitle("Time management Hacks");
        post.setBody("manage your time");
        post.setDate_created(new Date(new java.util.Date().getTime()));

        when(postService.findById(anyLong())).thenReturn(post);

        // Create a mock HTTP request to verify the expected result
        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1").with(user("user")))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Time management Hacks"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("manage your time"))
                .andExpect(status().isOk());
    }

    @Test
    public void savePostTest() throws Exception {

        //mock the post data that we have to save
        Post post = new Post();
        post.setId(0l);
        post.setTitle("Time management Hacks");
        post.setBody("manage your time");
        post.setDate_created(new Date(new java.util.Date().getTime()));


        when(postService.save(any(Post.class))).thenReturn(post);

        //mock request "/posts"
        mockMvc.perform(MockMvcRequestBuilders.post("/posts").with(user("user"))
                .content(new ObjectMapper().writeValueAsString(post))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Time management Hacks"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("manage your time"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date_created").exists());
    }

    @Test
    public void updatePostTest() throws Exception {

        //mock the post data that we have to save
        Post post = new Post();
        post.setId(2l);
        post.setTitle("Time management Hacks");
        post.setBody("manage your time");
        post.setDate_created(new Date(new java.util.Date().getTime()));


        when(postService.save(any(Post.class))).thenReturn(post);

        //mock request "/posts"
        mockMvc.perform(MockMvcRequestBuilders.put("/posts").with(user("user"))
                .content(new ObjectMapper().writeValueAsString(post))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Time management Hacks"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("manage your time"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date_created").exists());
    }

    @Test
    public void deletePostTest() throws Exception {

        //mock the delete data that we have to delete
        Post post = new Post();
        post.setId(2l);
        post.setTitle("Time management Hacks");
        post.setBody("manage your time");
        post.setDate_created(new Date(new java.util.Date().getTime()));


        when(postService.delete(anyLong())).thenReturn(post);

        //mock request "/posts/{id}"
        mockMvc.perform(MockMvcRequestBuilders.delete("/posts/2").with(user("user")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Time management Hacks"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("manage your time"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date_created").exists());
    }
}