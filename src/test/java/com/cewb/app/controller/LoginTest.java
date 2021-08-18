package com.cewb.app.controller;

import com.cewb.app.controller.auth.LoginController;
import com.cewb.app.service.UserService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {LoginController.class, UserService.class})
@WebMvcTest
public class LoginTest {
}
