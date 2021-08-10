package com.cewb.app;

import com.cewb.app.dto.response.ResponseMessage;
import com.cewb.app.model.Role;
import com.cewb.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MainApp implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(roleRepository.count() < 1) {
			Role admin = new Role();
			Role client = new Role();
			admin.setName("ADMIN");
			client.setName("USER");
			roleRepository.saveAll(Arrays.asList(admin, client));
		}
	}
}
