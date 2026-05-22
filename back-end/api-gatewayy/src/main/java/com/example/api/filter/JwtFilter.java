package com.example.api.filter;

import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import org.springframework.cloud.gateway.filter.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Component
public class JwtFilter implements GlobalFilter {
	//global filter runs for every incoming requests through gateway

    private static final String SECRET = "mysecretkeymysecretkeymysecretkey";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
    //same key used to validate and extract data
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    	
    	String path = exchange.getRequest().getURI().getPath();
    	// e.x /loans/1

    	//thsi for public apis (login API)
    	if (path.contains("/auth") ||
    		    path.contains("/v3/api-docs") ||
    		    path.contains("/swagger-ui") ||
    		    path.contains("/swagger-ui.html")) {
    		    return chain.filter(exchange);
    		}
    	
    	//this is for secured apis
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        //authorization: bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
            //will return 401 unauthorized
        }

        String token = authHeader.substring(7);

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String role = claims.get("role", String.class);
            //from token get role
            
            path = exchange.getRequest().getURI().getPath();

           

            if (path.contains("/pay")) {
                if (!"ADMIN".equals(role)) {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                    //if user get 403 forbidden
                }
            }

        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
            //if token invalid then 401 unauthorized
        }

        return chain.filter(exchange);
        //pass the request to service
    }
}