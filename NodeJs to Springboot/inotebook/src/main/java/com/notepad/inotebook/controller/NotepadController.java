package com.notepad.inotebook.controller;

import com.notepad.inotebook.dto.NotesDto;
import com.notepad.inotebook.model.NotepadModel;
import com.notepad.inotebook.response.Response;
import com.notepad.inotebook.service.NotepadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;


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
    public ResponseEntity<Response> fetchNotes() {
        return new ResponseEntity<Response>(notepadService.fetchNotes(), HttpStatus.OK);
    }

    @PostMapping("/addNotes")
    public ResponseEntity<Response> addNotes(@RequestBody NotesDto notesDto) {
        return new ResponseEntity<Response>(notepadService.addNotes(notesDto), HttpStatus.CREATED);
    }

    @PutMapping("/updateNotes/{id}")
    public ResponseEntity<Response> updateNotes(@RequestBody NotesDto notesDto, @PathVariable String id) {
        return new ResponseEntity<Response>(notepadService.updateNotes(notesDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteNotes/{id}")
    public ResponseEntity<Response> deleteNotes(@PathVariable String id) {
        return new ResponseEntity<Response>(notepadService.deleteNotes(id), HttpStatus.OK);
    }
}
