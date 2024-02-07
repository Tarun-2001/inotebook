package com.notepad.inotebook.service;

import com.notepad.inotebook.dto.NotesDto;
import com.notepad.inotebook.model.NotepadModel;
import com.notepad.inotebook.response.Response;

public interface NotepadService {
    public NotepadModel sample();
    public Response fetchNotes();
    public Response addNotes(NotesDto notesDto);
    public Response updateNotes(NotesDto notesDto, String Id);
    public Response deleteNotes(String id);
}
