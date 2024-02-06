package com.notepad.inotebook.response;

import com.notepad.inotebook.dto.NotesDto;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
public class Response {
    private NotesDto notesDto;
    private String message;
}
