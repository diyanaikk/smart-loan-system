package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayyApplication.class, args);
	}

}


//acts as a single entry point
//so all client requests go through API gateway which will route them to their services.