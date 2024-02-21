package com.notepad.inotebook.controller;

import com.notepad.inotebook.dto.AuthenticationDto;
import com.notepad.inotebook.model.AuthenticationModel;
import com.notepad.inotebook.repository.AuthenticationRespository;
import com.notepad.inotebook.serviceImpl.AuthenticationImpl;
import lombok.RequiredArgsConstructor;
import com.notepad.inotebook.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.notepad.inotebook.Utils.LOGIN_SUCCESSFULLY;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationRespository authenticationRespository;
    @Autowired
    AuthenticationImpl authenticationImpl;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody AuthenticationDto authenticationDto){

        var jwtToke = authenticationImpl.login(authenticationDto.getEmail(),authenticationDto.getPassword());
        Response response = new Response();
        response.setMessage(jwtToke);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Response> signUp(@RequestBody AuthenticationDto authenticationDto){
        return null;
    }
}
