package com.notepad.inotebook.serviceImpl;

import com.notepad.inotebook.model.AuthenticationModel;
import com.notepad.inotebook.repository.AuthenticationRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AuthenticationRespository authenticationRespository;
    private  AuthenticationModel user;
    @Override
    public UserDetails loadUserByUsername(String userDetails) throws UsernameNotFoundException {

        try {
            if(StringUtils.endsWithIgnoreCase(userDetails,"@gmail.com"))user = authenticationRespository.findByEmail(userDetails);
            else user = authenticationRespository.findById(userDetails);
        }catch (UsernameNotFoundException e) { throw new UsernameNotFoundException("User not found"); }
            Set<String> roles = new HashSet<>();
            roles.add("ROLE_ADMIN");
            return new User(user.getEmail(),user.getPassword(),userAuthority(roles));
    }

    private Collection< ? extends GrantedAuthority> userAuthority(Set<String> roles){
        return roles.stream().map((role)->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }
}
