package com.embltest.embl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories

public class EmblApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmblApplication.class, args);
	}
}
