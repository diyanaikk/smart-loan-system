package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;

import org.junit.jupiter.api.Test;
//imprts @Test
//tells JUnit that a method is a test case
import org.mockito.*;
//imports Mockito annotations like: @Mock, @InjectMock

import static org.mockito.Mockito.*;
//for mockito methods
import static org.junit.jupiter.api.Assertions.*;
//import assertion methods like: assertEquals()-> used for checking expected output

public class UserServiceTest {

    @Mock
    private UserRepository repo;
    //creates fake UserRepo
    //avoids actual db calls during testing

    @InjectMocks
    private UserService service;
    //creates actual UserService
    //injects mocked repo into it

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    } //initialize all Mockito annotations

    @Test
    public void testSaveUser() {
    	//test method starts here
    	//checks whether saveUser() works 

        User user = new User(null, "Diya", "diya@gmail.com", 50000.0, "Salaried");
        //sample user object
        when(repo.save(user)).thenReturn(user);
        //when repo.save(user) is called, then return 'user'
        //fake behavior
        User saved = service.saveUser(user);
        //calls actual service method
        //internally service calls: repo.save(user)
        //but mocked repo returns fake user
        assertNotNull(saved);
        //check if saved obj is not null
        assertEquals("Diya", saved.getName());
        //if name matches, then test passes
    }
}