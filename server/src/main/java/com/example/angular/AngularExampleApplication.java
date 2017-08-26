package com.example.angular;

import com.example.angular.config.AuthenticationSuccessHandlerCustom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@SpringBootApplication
public class AngularExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularExampleApplication.class, args);
	}
}
