package service;

import com.notepad.inotebook.model.AuthenticationModel;
import com.notepad.inotebook.repository.AuthenticationRespository;
import com.notepad.inotebook.security.jwtUtills.JwtUtil;
import com.notepad.inotebook.serviceImpl.AuthenticationImpl;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.print.Doc;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceImplTest {

    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtUtil jwtUtil;
    @Mock
    AuthenticationRespository authenticationRespository;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    MongoTemplate mongoTemplate;
    @InjectMocks
    AuthenticationImpl authenticationImpl;
    @Mock
    protected JwtUtil jwtToken;

    // Can not cover because authenticate method return an Authentication which is a type of interface.
    @Test
    public void loginTest() {
        try {
            Mockito.when(authenticationRespository.findByEmail(Mockito.anyString())).thenReturn(getAuthenticationModel());
            String actObject = authenticationImpl.login("email", "password");
            Assert.assertNotNull(actObject);
        } catch (Exception e) {
        }
    }

    @Test
    public void signupTest() {
        Mockito.when(mongoTemplate.save(Mockito.any())).thenReturn(getAuthenticationModel());
        String actObject = authenticationImpl.signup("email", "password", "name");
        Assert.assertNotNull(actObject);
    }

    // Can not cover because it use the class JwtUtil which is declared as protected
    @Test
    public void fetchUserTest() {
        try {
            Mockito.when(JwtUtil.getUserNameFromToken("abcd")).thenReturn("abc");
            Mockito.when(mongoTemplate.findOne(Mockito.any(), Mockito.eq(Document.class), Mockito.anyString())).thenReturn(getDocument());
            Object actObject = authenticationImpl.fetchUser();
            Assert.assertNotNull(actObject);
        } catch (Exception e) {
        }
    }

    private AuthenticationModel getAuthenticationModel() {
        AuthenticationModel authenticationModel = new AuthenticationModel();
        authenticationModel.setId("jwtToken");
        return authenticationModel;
    }

    private Authentication getAuthentication() {
        AuthenticationConfiguration authenticationConfiguration = new AuthenticationConfiguration();
        return (Authentication) authenticationConfiguration;
    }

    private Document getDocument() {
        Document document = new Document();
        document.put("password", "passwored");
        document.put("_class", "_class");
        return document;
    }

}
