package com.notepad.inotebook.repository;

import com.notepad.inotebook.model.NotepadModel;
import com.notepad.inotebook.serviceImpl.NotepadServicempl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotepadRepository extends MongoRepository<NotepadModel,String> {

    NotepadModel findByTitle(String title);
}
