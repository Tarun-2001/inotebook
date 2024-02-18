package com.notepad.inotebook.controller;

import com.notepad.inotebook.dto.AuthenticationDto;
import com.notepad.inotebook.model.AuthenticationModel;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody AuthenticationDto authenticationDto){
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<Response> signUp(@RequestBody AuthenticationDto authenticationDto){
        return null;
    }
}
