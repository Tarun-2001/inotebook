package com.notepad.inotebook.serviceImpl;

import com.notepad.inotebook.Utils;
import com.notepad.inotebook.dto.AuthenticationDto;
import com.notepad.inotebook.model.AuthenticationModel;
import com.notepad.inotebook.repository.AuthenticationRespository;
import com.notepad.inotebook.security.jwtUtills.JwtUtil;
import com.notepad.inotebook.service.AuthenticationService;
import org.bson.Document;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.ObjectName;
import javax.print.Doc;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthenticationImpl implements AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthenticationRespository authenticationRespository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    MongoTemplate mongoTemplate;
    protected String jwtToken;
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public  String login(String email, String password){
        var authentication  = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String userId = authenticationRespository.findByEmail(authentication.getName()).getId();
        System.out.println(userId);
        return jwtToken = JwtUtil.generateToken(userId);
    }

    @Override
    public String signup(String email, String password, String name){

        if(authenticationRespository.existsByEmail(email)){
            throw new RuntimeException("User Already Exists");
        }
        var encryptedPassword = bCryptPasswordEncoder.encode(password);
        AuthenticationModel authenticationModel = new AuthenticationModel();
        authenticationModel.setEmail(email);
        authenticationModel.setPassword(encryptedPassword);
        authenticationModel.setName(name);
        Date date = new Date();
        authenticationModel.setDate(date.getDate());
        AuthenticationModel savedUser = mongoTemplate.save(authenticationModel);
        return jwtToken = JwtUtil.generateToken(savedUser.getId());
    }

    @Override
    public Object fetchUser() {

        String userId = JwtUtil.getUserNameFromToken(jwtToken);
//        Object userDetails = authenticationRespository.findById(userId);
        Query query = new Query(Criteria.where("_id").is(userId));
        Document userDetails = mongoTemplate.findOne(query, Document.class, "users");
        assert userDetails != null;
        userDetails.remove("password");
        userDetails.remove("_class");
        userDetails.put("_id",userId);
        return userDetails;
    }
}
