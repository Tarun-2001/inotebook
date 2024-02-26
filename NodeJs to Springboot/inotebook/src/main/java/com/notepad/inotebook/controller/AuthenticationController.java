package com.notepad.inotebook.controller;

import com.notepad.inotebook.dto.AuthenticationDto;
import com.notepad.inotebook.repository.AuthenticationRespository;
import com.notepad.inotebook.response.AuthenticationResponse;
import com.notepad.inotebook.serviceImpl.AuthenticationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.JobKOctets;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationRespository authenticationRespository;
    @Autowired
    AuthenticationImpl authenticationImpl;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AuthenticationResponse authenticationResponse;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationDto authenticationDto){

        String jwtToke = authenticationImpl.login(authenticationDto.getEmail(),authenticationDto.getPassword());
        authenticationResponse.setToken(jwtToke);
        authenticationResponse.setSuccess(true);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<AuthenticationResponse> signUp(@RequestBody AuthenticationDto authenticationDto){

        String jwtToken = authenticationImpl.signup(authenticationDto.getEmail(),authenticationDto.getPassword(),authenticationDto.getName());
        authenticationResponse.setToken(jwtToken);
        authenticationResponse.setSuccess(true);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @GetMapping("/fetchUser")
    public ResponseEntity fetchUser(){

        Object userDetails = authenticationImpl.fetchUser();
        Map<String,Object> res = new LinkedHashMap<>();
        return ResponseEntity.status(HttpStatus.OK).body(userDetails);
    }

}
