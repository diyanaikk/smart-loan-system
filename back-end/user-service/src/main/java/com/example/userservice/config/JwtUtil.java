//utility class for JWT
//-> generates token
//-> validates token
//-> extracts data from token


package com.example.userservice.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
//from jjwt lib
//used to create and parse JWT

import java.security.Key; //for signing token
import java.util.Date; //for issue and expiry time

public class JwtUtil {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkey";
    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
    //used to sign and verify token

    public static String generateToken(String username, String role) {  //takes uname and role
        return Jwts.builder()  //starts token creation
                .setSubject(username)  //store uname
                .claim("role", role)  //custom data -> role
                .setIssuedAt(new Date()) //token creation time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  //expiry=1 hour
                .signWith(key) //signs token with secret key
                .compact();  //converts into JWT string
    }

    public static String validateToken(String token) {
    	//validates signatures, checks expiry
        return Jwts.parserBuilder()  //starts parsing
                .setSigningKey(key) //uses same key to verify token
                .build()  
                .parseClaimsJws(token)  //validates token
				/*
				 * Throws exception if:
				 * 		invalid 
				 * 		expired 
				 * 		tampered
				 */
                .getBody()
                .getSubject();
        		//extracts uname from token
    }
}


/*
 * JwtUtil = helper class 
 * JwtFilter = actually uses it
 */




//adding a digital signature to the token using a secret key