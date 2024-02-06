package com.notepad.inotebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotepadModel {
    @Id
    private  String id;
    private  String title;
    private  String tag;
    private  String description;
    private  Date date;
    private  AuthenticationModel authenticationModel;
}
