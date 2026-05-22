package com.example.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
//converts this app to eureka
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}


//this class starts eureka server (service registry)
//all microservices register here and discover eo

//instead of hardcoding to http://localhost:8081/users/1
// i use: http://USER-SERVICE/users/1
//eureka knows where user-service is running, so it routes request correctly