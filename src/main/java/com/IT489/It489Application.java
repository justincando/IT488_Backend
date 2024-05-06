package com.IT489;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class It489Application {

	public static void main(String[] args) {
		SpringApplication.run(It489Application.class, args);
	}

	@Bean
	public BCrypt.Hasher getHasher()
	{
		return BCrypt.withDefaults();
	}
}
