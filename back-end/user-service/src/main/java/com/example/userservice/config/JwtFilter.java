//intercepts every req, checks for JWT, and will set authentication

package com.example.userservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.io.IOException;
import java.util.Collections;

public class JwtFilter extends OncePerRequestFilter {
	//runs once per request
	

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
    //runs for every incoming request
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        //looks for Authorization : Bearer <token>

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);
            //remove "Bearer "

            try {
                String username = JwtUtil.validateToken(token);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_ADMIN")) //means no roles, no authorities
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);
                //Spring considers request as authenticated
                
                
            } catch (Exception e) {
              //if token is invalid/ expired/ malformed, dont need to crash app, just ignore
            }
        }

        filterChain.doFilter(request, response);
        //pass the request to next controller
    }
}