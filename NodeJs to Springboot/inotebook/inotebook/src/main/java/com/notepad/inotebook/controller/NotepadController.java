package com.notepad.inotebook.controller;

import com.notepad.inotebook.model.NotepadModel;
import com.notepad.inotebook.service.NotepadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/note")
public class NotepadController {

    @Autowired
    NotepadService notepadService;
    @PostMapping("/hi")
    public void sample(@RequestBody NotepadModel notepadModel){
        notepadService.sample(notepadModel);
    }
}
