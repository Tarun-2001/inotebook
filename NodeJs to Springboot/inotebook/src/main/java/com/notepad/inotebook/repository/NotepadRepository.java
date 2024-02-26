package com.notepad.inotebook.repository;

import com.notepad.inotebook.model.NotepadModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotepadRepository extends MongoRepository<NotepadModel,String> {

    NotepadModel findByTitle(String title);
    Optional<NotepadModel> findById(String id);

    NotepadModel findByAuthenticationModel(String id);
}
