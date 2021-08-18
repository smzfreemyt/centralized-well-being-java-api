package com.cewb.app.controller;

import com.cewb.app.controller.auth.LoginController;
import com.cewb.app.dto.request.UserRequestDto;
import com.cewb.app.model.User;
import com.cewb.app.security.dto.JwtResponse;
import com.cewb.app.security.dto.LoginSecurityDto;
import com.cewb.app.security.service.UserDetailsServiceImpl;
import com.cewb.app.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {LoginController.class, UserDetailsServiceImpl.class})
@WebMvcTest
public class LoginTest {

    @Autowired
    LoginController loginController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserDetailsServiceImpl userService;

    @Test
    public void loginTest() throws Exception {
        UserRequestDto user = new UserRequestDto("Sample user", "Sample@user.com", "test12345", "ADMIN");
        User newUser = new User();

        LoginSecurityDto requestLogin = new LoginSecurityDto();
        requestLogin.setEmail("user@test.com");
        requestLogin.setPassword("test123");

        JwtResponse jwt = new JwtResponse();
        jwt.setToken("token");
        jwt.setType("Bearer");

        when(userService.doLogin(any(LoginSecurityDto.class))).thenReturn(jwt);

        // Create a mock HTTP request to verify the expected result
        mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/login")
                .content(new ObjectMapper().writeValueAsString(requestLogin))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("token"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("Bearer"))
                .andReturn();
    }
}
