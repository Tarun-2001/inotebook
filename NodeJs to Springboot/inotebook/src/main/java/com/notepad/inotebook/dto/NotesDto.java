package com.notepad.inotebook.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NotesDto {
    private  String title;
    private  String tag;
    private  String description;
    private Date date;
    private String message;
}
