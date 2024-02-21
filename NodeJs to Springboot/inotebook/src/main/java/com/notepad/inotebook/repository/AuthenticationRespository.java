package com.notepad.inotebook.repository;

import com.notepad.inotebook.model.AuthenticationModel;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRespository extends MongoRepository<AuthenticationModel,Integer> {
    AuthenticationModel findByEmail(String username);
}
