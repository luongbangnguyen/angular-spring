package com.example.angular.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by nlbang on 9/1/2017.
 */

public class JsonUserNameAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        if(!request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException("Authentication content type not supported: " + request.getContentType());
        }

        UsernamePasswordAuthentication usernamePassword = getUsernamePassword(request);

        String username = usernamePassword.getUsername();
        String password = usernamePassword.getPassword();
        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        this.setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);

    }

    private UsernamePasswordAuthentication getUsernamePassword(HttpServletRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder jsonValue = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jsonValue.append(line);
            }
            return mapper.readValue(jsonValue.toString(), UsernamePasswordAuthentication.class);
        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
    }
}
