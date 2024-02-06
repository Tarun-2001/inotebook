package com.notepad.inotebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class AuthenticationModel {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private Date data;
}
