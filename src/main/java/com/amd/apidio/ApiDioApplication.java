package com.amd.apidio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiDioApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiDioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
	}
}
