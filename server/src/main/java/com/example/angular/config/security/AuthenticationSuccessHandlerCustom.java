package com.example.angular.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by bangnl on 8/26/17.
 */
@Component
public class AuthenticationSuccessHandlerCustom extends SimpleUrlAuthenticationSuccessHandler{

    private final ObjectMapper mapper;

    AuthenticationSuccessHandlerCustom(MappingJackson2HttpMessageConverter messageConverter) {
        this.mapper = messageConverter.getObjectMapper();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);

        Object userDetails =  authentication.getPrincipal();


        PrintWriter writer = response.getWriter();
        mapper.writeValue(writer, userDetails);
        writer.flush();
    }

}
