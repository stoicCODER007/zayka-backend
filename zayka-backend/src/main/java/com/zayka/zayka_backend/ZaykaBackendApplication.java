package com.zayka.zayka_backend;

import com.zayka.zayka_backend.model.User;
import com.zayka.zayka_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ZaykaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZaykaBackendApplication.class, args);
	}
	@Bean
	CommandLineRunner run(UserRepository userRepo) {
		return args -> {
			if (userRepo.findByEmail("admin@zayka.com").isEmpty()) {
				userRepo.save(User.builder()
						.email("admin@zayka.com")
						.password("123456") // We’ll secure this later with BCrypt
						.build());
				System.out.println("Inserted default user");
			}
		};
	}

}
