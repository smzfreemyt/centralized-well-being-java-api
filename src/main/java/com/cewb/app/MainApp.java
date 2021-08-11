package com.cewb.app;

import com.cewb.app.config.Config;
import com.cewb.app.controller.TestController;
import com.cewb.app.model.Role;
import com.cewb.app.model.User;
import com.cewb.app.repository.RoleRepository;
import com.cewb.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MainApp implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		if(roleRepository.count() < 1) {
//			Role admin = new Role();
//			Role client = new Role();
//			admin.setName("ADMIN");
//			client.setName("USER");
//			roleRepository.saveAll(Arrays.asList(admin, client));
//		}

//		System.out.println("Adding...");
//		User user1 = new User("sam", "password", "email");
//		Role role = new Role(Config.ROLE_USER_TEXT);
//		user1.getRoles().add(role);
//		role.getUsers().add(user1);
//		this.userRepository.save(user1);
	}
}
