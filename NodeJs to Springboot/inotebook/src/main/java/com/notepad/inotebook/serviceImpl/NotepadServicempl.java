package com.notepad.inotebook.serviceImpl;

import com.notepad.inotebook.dto.NotesDto;
import com.notepad.inotebook.repository.NotepadRepository;
import com.notepad.inotebook.model.NotepadModel;
import com.notepad.inotebook.response.Response;
import com.notepad.inotebook.service.NotepadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotepadServicempl implements NotepadService {
    @Autowired
    NotepadRepository notepadRepository;

    String userID = "6500b2d1324f7cdeca15b8a0";

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    Response response;

    public NotepadModel sample(){
        System.out.println(mongoTemplate.findAll(NotepadModel.class));
        System.out.println(mongoTemplate.findById("6500b2d1324f7cdeca15b8a0",NotepadModel.class).toString()) ;
//        mongoTemplate.save(notepad);
//        System.out.println(mongoTemplate.find(notepad.getTitle()));
//        notepadRepository.save(notepad);
        return mongoTemplate.findById("6500b2d1324f7cdeca15b8a0",NotepadModel.class);
    }
    public Response fetchNotes(){
        try{
            NotepadModel fetchedData = mongoTemplate.findById(userID,NotepadModel.class);
            NotesDto data = modelMapper.map(fetchedData,NotesDto.class);
            response.setNotesDto(data);
            response.setMessage("Data Fetched Successfully");
            return response;
        }
        catch (Exception ignored){}
        return null;
    }
}
