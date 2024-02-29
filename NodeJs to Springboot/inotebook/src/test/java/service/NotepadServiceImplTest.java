package service;

import com.notepad.inotebook.dto.NotesDto;
import com.notepad.inotebook.model.AuthenticationModel;
import com.notepad.inotebook.model.NotepadModel;
import com.notepad.inotebook.repository.AuthenticationRespository;
import com.notepad.inotebook.repository.NotepadRepository;
import com.notepad.inotebook.response.Response;
import com.notepad.inotebook.security.jwtUtills.JwtUtil;
import com.notepad.inotebook.serviceImpl.NotepadServicempl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.platform.engine.support.hierarchical.Node;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

@RunWith(MockitoJUnitRunner.class)
public class NotepadServiceImplTest {
    @InjectMocks
    NotepadServicempl notepadServicempl;
    @Mock
    NotepadRepository notepadRepository;
    @Mock
    MongoTemplate mongoTemplate;
    @Mock
    ModelMapper modelMapper;
    @Mock
    Response response;
    @Mock
    AuthenticationRespository authenticationRespository;

    @BeforeClass
    public static void setUp() {
        JwtUtil.ID = "jwtToken";
    }

    @Test
    public void fetchNotesTest(){
        List<NotepadModel> notepadList = new ArrayList<>();
        notepadList.add(getNotepadModel());
        Mockito.when(notepadRepository.findAllByAuthenticationModel(Mockito.any())).thenReturn(notepadList);
        Response actObject = notepadServicempl.fetchNotes();
        Assert.assertNotNull(actObject);
    }
    @Test
    public void addNotesTest(){
        Mockito.when(authenticationRespository.findById(Mockito.anyString())).thenReturn(getAuthenticationModel());
        Mockito.when(modelMapper.map(Mockito.any(),Mockito.eq(NotepadModel.class))).thenReturn(getNotepadModel());
        Mockito.when(mongoTemplate.save(Mockito.any())).thenReturn(getNotepadModel());
        Mockito.when(modelMapper.map(Mockito.any(),Mockito.eq(NotesDto.class))).thenReturn(getNotepadDto());
        Response actObject = notepadServicempl.addNotes(getNotepadDto());
        Assert.assertNotNull(actObject);
    }
    @Test
    public void updateNotesTest(){
        Mockito.when(mongoTemplate.findById(Mockito.anyString(), Mockito.eq(NotepadModel.class))).thenReturn(getNotepadModel());
        Mockito.when(notepadRepository.save(Mockito.any())).thenReturn(getNotepadModel());
        Mockito.when(modelMapper.map(Mockito.any(),Mockito.eq(NotesDto.class))).thenReturn(getNotepadDto());
        Response actObject = notepadServicempl.updateNotes(getNotepadDto(),"Id");
        Assert.assertNotNull(actObject);
    }
    @Test
    public void deleteNotesTest(){
        Mockito.when(mongoTemplate.findById(Mockito.anyString(), Mockito.eq(NotepadModel.class))).thenReturn(getNotepadModel());
        Mockito.when(mongoTemplate.remove(Mockito.any(),Mockito.eq(NotepadModel.class))).thenReturn(null);
        Response actObject = notepadServicempl.deleteNotes("Id");
        Assert.assertNotNull(actObject);

    }
    private AuthenticationModel getAuthenticationModel(){
        AuthenticationModel authenticationModel = new AuthenticationModel();
        authenticationModel.setId("jwtToken");
        return authenticationModel;
    }
    private NotepadModel getNotepadModel(){
        NotepadModel notepadModel = new NotepadModel();
        notepadModel.setAuthenticationModel(getAuthenticationModel());
        return notepadModel;
    }
    private NotesDto getNotepadDto(){
        NotesDto notesDto = new NotesDto();
        notesDto.setTitle("title");
        notesDto.setDescription("description");

        return notesDto;
    }

}
