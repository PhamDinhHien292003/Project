package com.example.vgc_project;

import jakarta.persistence.Cacheable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VgcProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(VgcProjectApplication.class, args);
	}

}
