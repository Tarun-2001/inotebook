package com.notepad.inotebook.serviceImpl;

import com.notepad.inotebook.repository.NotepadRepository;
import com.notepad.inotebook.model.NotepadModel;
import com.notepad.inotebook.service.NotepadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotepadServicempl implements NotepadService {
    @Autowired
    NotepadRepository notepadRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public void sample(NotepadModel notepad){
        System.out.println(mongoTemplate.findAll(NotepadModel.class));
        System.out.println(mongoTemplate.findById("6500b2d1324f7cdeca15b8a0",NotepadModel.class));
        mongoTemplate.save(notepad);
        System.out.println(notepadRepository.findByTitle(notepad.getTitle()));
        notepadRepository.save(notepad);

    }
}
