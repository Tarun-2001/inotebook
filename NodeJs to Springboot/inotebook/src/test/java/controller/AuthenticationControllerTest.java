package controller;

import com.notepad.inotebook.controller.AuthenticationController;
import com.notepad.inotebook.dto.AuthenticationDto;
import com.notepad.inotebook.response.AuthenticationResponse;
import com.notepad.inotebook.serviceImpl.AuthenticationImpl;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import javax.print.Doc;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {

    @InjectMocks
    AuthenticationController authenticationController;
    @Mock
    AuthenticationImpl authenticationImpl;
    @Mock
    AuthenticationResponse authenticationResponse;

    @Test
    public void loginTest() {

        Mockito.when(authenticationImpl.login(Mockito.anyString(), Mockito.anyString())).thenReturn("abcd");
        ResponseEntity<AuthenticationResponse> actualObj = authenticationController.login(getAuthenticationDto());
        org.junit.Assert.assertNotNull(actualObj);
    }

    @Test
    public void signUpTest() {

        Mockito.when(authenticationImpl.signup(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn("abcd");
        ResponseEntity<AuthenticationResponse> actulaObj = authenticationController.signUp(getAuthenticationDto());
        org.junit.Assert.assertNotNull(actulaObj);
    }

    @Test
    public void fetchUserTest() {

        try {
            Mockito.when(authenticationImpl.fetchUser()).thenReturn(getDocument());
            ResponseEntity<javax.swing.text.Document> actulaObj = authenticationController.fetchUser();
            org.junit.Assert.assertNotNull(actulaObj);
        } catch (Exception e) {}
    }

    private AuthenticationDto getAuthenticationDto() {
        AuthenticationDto authenticationDto = new AuthenticationDto();
        authenticationDto.setName("abcd");
        authenticationDto.setEmail("abcd@gmail.com");
        authenticationDto.setPassword("abc");
        return authenticationDto;
    }

    private Document getDocument() {
        Document document = new Document();
        document.put("Key", "Value");
        return document;
    }
}
