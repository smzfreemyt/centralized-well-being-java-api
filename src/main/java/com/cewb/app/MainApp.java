package com.cewb.app;

import com.cewb.app.config.ConfigRole;
import com.cewb.app.model.Role;
import com.cewb.app.model.User;
import com.cewb.app.repository.RoleRepository;
import com.cewb.app.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
@Log4j2
@Configuration
public class MainApp implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Environment env;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (this.userRepository.count() < 1) {
			try {
				String name  = env.getProperty("cewbs.admin.name");
				String email = env.getProperty("cewbs.admin.email");
				String pass  = passwordEncoder.encode(env.getProperty("cewbs.admin.password"));
				User user = new User(name,pass, email);
				Role role = new Role(ConfigRole.ROLE_ADMIN);
				user.getRoles().add(role);
				role.getUsers().add(user);
				this.userRepository.save(user);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}
}
