package com.gym.gymoficial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableSwagger2
public class GymOficialApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymOficialApplication.class, args);
	}

		@Bean
		public PasswordEncoder getPasswordEncoder(){
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
		}
}
