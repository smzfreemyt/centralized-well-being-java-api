package com.cewb.app;

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
			Role client = new Role();
			Role admin = new Role();
			client.setName("USER");
			admin.setName("ADMIN");
			roleRepository.saveAll(Arrays.asList(client, admin));
		}
	}
}
