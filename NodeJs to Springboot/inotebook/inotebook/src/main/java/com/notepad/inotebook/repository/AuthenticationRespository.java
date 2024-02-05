package com.notepad.inotebook.repository;

import com.notepad.inotebook.model.AuthenticationModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthenticationRespository extends MongoRepository<AuthenticationModel,Integer> {
}
