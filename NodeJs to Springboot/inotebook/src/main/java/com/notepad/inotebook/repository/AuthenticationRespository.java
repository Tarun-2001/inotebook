package com.notepad.inotebook.repository;

import com.notepad.inotebook.model.AuthenticationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRespository extends MongoRepository<AuthenticationModel,Integer> {
    Object findByName(String username);
}
