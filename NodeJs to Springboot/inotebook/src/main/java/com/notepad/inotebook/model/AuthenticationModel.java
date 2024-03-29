package com.notepad.inotebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.ElementType;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class AuthenticationModel {

    @Id
    private String id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private Integer date;
}
