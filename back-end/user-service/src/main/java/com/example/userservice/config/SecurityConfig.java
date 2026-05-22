//spring security configuration
//decides:
	// -> which APIs are public 
	// -> which APIs need authentication
	// -> how JWT is validated


package com.example.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //marks as configuration class
public class SecurityConfig {

	//Security Filter Chain Bean
	//this will define security rules
	//HttpSecurity is used to configure this
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
        	//cross-site request forgery
				/*
				 * A user is tricked into sending a request 
				 * without their knowledge
				 * using their already logged-in session
				 */
            .csrf(csrf -> csrf.disable())  
            //csrf is protection for browser based attacks  (disabled bc we i am using JWT)
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers(
            			    "/auth/**",
            			    "/users/**",
            			    "/swagger-ui/**",
            			    "/v3/api-docs/**",
            			    "/users/**"
            			).permitAll()
                // /auth/** -> login allowed
                // swagger urls is open for testing
                .anyRequest().authenticated()
                //rest needs valid JWT token
            )
            .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
        //runs JwtFilter before spring security
        //extracts token
        //validates it
        //sets user authentication
        
        
        return http.build();
    }
}