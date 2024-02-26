package com.notepad.inotebook.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface AuthenticationService {
    String login(String email, String password);
    String signup(String email, String password, String name);
    Object fetchUser();
}
