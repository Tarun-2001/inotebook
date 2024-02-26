package com.notepad.inotebook.security.config;
import com.notepad.inotebook.security.jwtUtills.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JWTAuthenticationFiler extends OncePerRequestFilter {

    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Pattern pattern = Pattern.compile(".*api/auth.*");
        Matcher matcher = pattern.matcher(request.getRequestURL());
        if(matcher.find()){
            filterChain.doFilter(request,response);
            return;
        }

//      Fetch Token from request
        String jwtToken = getTokenFromRequest(request);

        if(jwtToken!=null){
            if(JwtUtil.validateToken(jwtToken)){
                //Get username from Token
                String userName = JwtUtil.getUserNameFromToken(jwtToken);

                //Fetch user details from userName
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set Authentication token to security context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // Pass the request and response to next filter
        filterChain.doFilter(request,response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {

        //Extract Token from header
        String authheader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(authheader.substring(7))&&authheader.startsWith("Bearer ")) {
            return authheader.substring(7);
        }
        return null;

    }
}
