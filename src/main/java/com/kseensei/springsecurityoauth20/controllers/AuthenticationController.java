package com.kseensei.springsecurityoauth20.controllers;

import com.kseensei.springsecurityoauth20.entities.User;
import com.kseensei.springsecurityoauth20.exceptions.SecurityExceptionHandler;
import com.kseensei.springsecurityoauth20.requests.UserRequest;
import com.kseensei.springsecurityoauth20.services.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "v1/oauth2")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 
     * @param user
     * @return
     * @throws SecurityExceptionHandler
     */
    @PostMapping(value = "register")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) throws SecurityExceptionHandler {
        User response = authenticationService.registerUser(null, null, userRequest);
        return ResponseEntity.ok(response);
    }
}
