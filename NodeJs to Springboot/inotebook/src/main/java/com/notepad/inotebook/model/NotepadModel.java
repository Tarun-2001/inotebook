package com.notepad.inotebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.jsr310.LocalDateTimeCodec;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "id")
    private  AuthenticationModel authenticationModel;
}
