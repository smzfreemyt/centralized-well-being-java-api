package com.cewb.app.controller;

import com.cewb.app.model.Post;
import com.cewb.app.repository.RoleRepository;
import com.cewb.app.service.PostService;
import org.junit.jupiter.api.Test;

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
import org.springframework.test.context.ContextConfiguration;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
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
        mockMvc.perform(MockMvcRequestBuilders.get("/post/1").with(user("ADMIN")).with(csrf()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Time management Hacks"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("manage your time"))
                .andExpect(status().isOk());
    }
}