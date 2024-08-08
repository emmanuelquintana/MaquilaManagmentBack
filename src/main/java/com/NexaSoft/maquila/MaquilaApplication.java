package com.NexaSoft.maquila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.NexaSoft.maquila.app.domain.repository")
@EntityScan(basePackages = "com.NexaSoft.maquila.app.domain.entity")
public class MaquilaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaquilaApplication.class, args);
	}

}
