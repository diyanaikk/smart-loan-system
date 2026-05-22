package com.example.userservice.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.userservice.config.*;
//imports config class (jwtutil)

@RestController
@RequestMapping("/auth")
public class AuthController {

	@PostMapping("/login")
	// {/auth/login}
	public String login(@RequestParam String username) {
		// request param: takes input from request parameter
		//ex: POST /auth/login?username=admin

	    String role = "USER";   //default role

	    if (username.equalsIgnoreCase("admin")) {
	        role = "ADMIN";
	    }

	    return JwtUtil.generateToken(username, role);
	    //calls JwtUtil class
	    //generates token that has -> username+role
	}
}