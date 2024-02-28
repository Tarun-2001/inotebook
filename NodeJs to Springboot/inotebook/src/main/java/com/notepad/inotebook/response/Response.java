package com.notepad.inotebook.response;

import com.notepad.inotebook.dto.NotesDto;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Response {
    private List<NotesDto> notesDto;
    private String message;
}
