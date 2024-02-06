package com.notepad.inotebook.controller;

import com.notepad.inotebook.dto.NotesDto;
import com.notepad.inotebook.model.NotepadModel;
import com.notepad.inotebook.response.Response;
import com.notepad.inotebook.service.NotepadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/note")
public class NotepadController {

    @Autowired
    NotepadService notepadService;

//    @GetMapping
//    public NotepadModel sample(){
//        return notepadService.sample();
//    }
    @GetMapping("/fetchNotes")
    public ResponseEntity<Response> fetchNotes(){
        return new ResponseEntity<Response>(notepadService.fetchNotes(),HttpStatus.OK);
    }
}
