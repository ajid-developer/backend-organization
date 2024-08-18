package com.bvk.test.backend_organization;

import com.bvk.test.backend_organization.entities.User;
import com.bvk.test.backend_organization.enums.RoleEnum;
import com.bvk.test.backend_organization.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendOrganizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendOrganizationApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

//	@Bean
	CommandLineRunner runner(UserService userService, PasswordEncoder passwordEncoder) {
		return args -> {
			User user = User.builder()
					.name("Ajid")
					.email("ajid@gmail.com")
					.password(passwordEncoder.encode("password"))
					.role(RoleEnum.ADMIN)
					.build();

			userService.saveUser(user);
		};
	}
}