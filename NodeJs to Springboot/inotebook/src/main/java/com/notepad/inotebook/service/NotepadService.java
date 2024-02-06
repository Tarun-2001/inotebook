package com.notepad.inotebook.service;

import com.notepad.inotebook.dto.NotesDto;
import com.notepad.inotebook.model.NotepadModel;
import com.notepad.inotebook.response.Response;

public interface NotepadService {
    public NotepadModel sample();
    public Response fetchNotes();
}
