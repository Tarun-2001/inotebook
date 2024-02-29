package com.notepad.inotebook.serviceImpl;

import com.notepad.inotebook.dto.NotesDto;
import com.notepad.inotebook.model.AuthenticationModel;
import com.notepad.inotebook.repository.AuthenticationRespository;
import com.notepad.inotebook.repository.NotepadRepository;
import com.notepad.inotebook.model.NotepadModel;
import com.notepad.inotebook.response.Response;
import com.notepad.inotebook.security.jwtUtills.JwtUtil;
import com.notepad.inotebook.service.NotepadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotepadServicempl implements NotepadService {
    @Autowired
    NotepadRepository notepadRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    Response response;
    @Autowired
    AuthenticationRespository authenticationRespository;


    public Response fetchNotes() throws NumberFormatException {
        AuthenticationModel user = authenticationRespository.findById(JwtUtil.ID);
        List<NotepadModel> fetchedData = notepadRepository.findAllByAuthenticationModel(user);
        List<NotesDto> data = fetchedData.stream().map(ele -> modelMapper.map(ele, NotesDto.class)).collect(Collectors.toList());
        response.setNotesDto(data);
        response.setMessage("Data Fetched Successfully");
        return response;
    }

    public Response addNotes(@RequestBody NotesDto notesDto) throws NumberFormatException {


        if (notesDto.getTitle().length() < 3) {
            throw new NumberFormatException();
        }
        ;
        if (notesDto.getDescription() != null && notesDto.getDescription().length() <= 5) throw new RuntimeException();
        NotepadModel data = modelMapper.map(notesDto, NotepadModel.class);
        AuthenticationModel user = authenticationRespository.findById(JwtUtil.ID);
        if (user == null) {
            throw new UsernameNotFoundException("User Not found");
        }
        ;
        data.setAuthenticationModel(user);
        NotepadModel savedData = mongoTemplate.save(data);
        LocalDateTime date = LocalDate.now().atStartOfDay();
        savedData.setDate(date);
        response.setNotesDto(Collections.singletonList(modelMapper.map(savedData, NotesDto.class)));
        response.setMessage("Note Added Successfully");
        return response;
    }

    public Response updateNotes(NotesDto notesDto, String id) {
        if (notesDto.getTitle().length() < 3) {
            throw new NumberFormatException();
        }
        ;
        if (notesDto.getDescription() != null && notesDto.getDescription().length() <= 5) throw new RuntimeException();
        NotepadModel fetchedData = mongoTemplate.findById(id, NotepadModel.class);
        if (fetchedData == null) throw new RuntimeException("Invalid Id ");
        if (!fetchedData.getAuthenticationModel().getId().equals(JwtUtil.ID))
            throw new RuntimeException("You can not perfom this operation");
        fetchedData.setTag(notesDto.getTag());
        fetchedData.setTitle(notesDto.getTitle());
        NotepadModel updatedNotepad = notepadRepository.save(fetchedData);
        response.setNotesDto(Collections.singletonList(modelMapper.map(updatedNotepad, NotesDto.class)));
        response.setMessage("Notes updated successfully");
        return response;
    }

    public Response deleteNotes(String id) {
        NotepadModel fetchedData = mongoTemplate.findById(id, NotepadModel.class);
        if (fetchedData == null) throw new RuntimeException("Invalid Id ");
        if (!fetchedData.getAuthenticationModel().getId().equals(JwtUtil.ID))
            throw new RuntimeException("You can not perform this operation");
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, NotepadModel.class);
        response.setMessage("Deleted Successfully");
        response.setNotesDto(Collections.singletonList(modelMapper.map(fetchedData, NotesDto.class)));
        return response;
    }

}
