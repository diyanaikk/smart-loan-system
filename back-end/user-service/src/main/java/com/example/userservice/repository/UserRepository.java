package com.example.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}



//repo talks to db 
//user -> table
//long -> type of primary key (id)