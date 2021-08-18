package com.cewb.app.controller;

import com.cewb.app.controller.auth.LoginController;
import com.cewb.app.dto.request.UserRequestDto;
import com.cewb.app.model.User;
import com.cewb.app.security.dto.LoginSecurityDto;
import com.cewb.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {LoginController.class, UserService.class})
@WebMvcTest
public class LoginTest {
    LoginController loginController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void loginTest() throws Exception {
        UserRequestDto user = new UserRequestDto("Sample user", "Sample@user.com", "test12345", "ADMIN");
        User newUser = new User();
        when(userService.save(user)).thenReturn(newUser);

        LoginSecurityDto requestLogin = new LoginSecurityDto();
        requestLogin.setEmail(newUser.getEmail());
        requestLogin.setPassword(newUser.getPassword());
    }
}
