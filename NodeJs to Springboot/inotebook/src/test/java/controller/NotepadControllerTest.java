package controller;

import com.notepad.inotebook.controller.NotepadController;
import com.notepad.inotebook.dto.NotesDto;
import com.notepad.inotebook.response.Response;
import com.notepad.inotebook.service.NotepadService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class NotepadControllerTest {
    @Mock
    NotepadService notepadService;
    @InjectMocks
    NotepadController notepadController;
    ResponseEntity<Response> response = new ResponseEntity<>(HttpStatus.OK);

    @Test
    public void fetchNotesTest() {

        Mockito.when(notepadService.fetchNotes()).thenReturn(getResponse());
        ResponseEntity<Response> actualObj = notepadController.fetchNotes();
        Assert.assertNotNull(actualObj);
    }

    @Test
    public void addNotesTest() {
        Mockito.when(notepadService.addNotes(getNotesDto())).thenReturn(getResponse());
        ResponseEntity<Response> actualObj = notepadController.addNotes(getNotesDto());
        Assert.assertNotNull(actualObj);
    }

    @Test
    public void updateNotesTest() {
        Mockito.when(notepadService.updateNotes(getNotesDto(), "UpdateNotes")).thenReturn(getResponse());
        ResponseEntity<Response> actualObj = notepadController.updateNotes(getNotesDto(), "UpdateNotes");
        Assert.assertNotNull(actualObj);
    }

    @Test
    public void deleteNotesTest() {
        Mockito.when(notepadService.deleteNotes(Mockito.anyString())).thenReturn(getResponse());
        ResponseEntity<Response> actualObj = notepadController.deleteNotes(Mockito.anyString());
        Assert.assertNotNull(actualObj);
    }

    private NotesDto getNotesDto() {
        NotesDto notesDto = new NotesDto();
        notesDto.setTitle("title");
        notesDto.setDescription("description");
        notesDto.setTag("tag");
        return notesDto;
    }

    private Response getResponse() {
        return new Response();
    }

}
