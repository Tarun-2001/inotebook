package com.notepad.inotebook.security.config;

import com.notepad.inotebook.security.config.JWTAuthenticationFiler;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {
    @Autowired
    private  AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private JWTAuthenticationFiler jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // 1. Disable cors origin
        httpSecurity.cors((cors)->{cors.disable();});
        // 2. Disable csrf
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//         3. Permit all authentication calls and authenticate remaining all requests.
        httpSecurity.authorizeHttpRequests((requestMatch) -> {
            requestMatch.requestMatchers("/api/auth/**").permitAll()
                    .anyRequest()
                    .authenticated();
        });

//        httpSecurity.authorizeRequests().anyRequest().authenticated();

        // 4. Handle Exception at entry point while authentication
        httpSecurity.exceptionHandling((execption)->{execption.authenticationEntryPoint(authenticationEntryPoint);});

        // 5. Set Session policy as stateless because we are using jwt token
        httpSecurity.sessionManagement(sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 6. Add JWT Authentication filter
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//        System.out.println(httpSecurity);
        return httpSecurity.build();
    }

}
