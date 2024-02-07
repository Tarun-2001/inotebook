package com.notepad.inotebook.dto;

import com.notepad.inotebook.model.AuthenticationModel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class NotesDto {
    private  String title;
    private  String tag;
    private String description;
    private LocalDateTime date;
    private AuthenticationModel authenticationModel;
}
