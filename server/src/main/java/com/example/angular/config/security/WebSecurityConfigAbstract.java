package com.example.angular.config.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by nlbang on 9/11/2017.
 */
public abstract class WebSecurityConfigAbstract extends WebSecurityConfigurerAdapter {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    protected WebSecurityConfigAbstract(AuthenticationSuccessHandler authenticationSuccessHandler,
                      AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                .withUser("user").password("user").roles("USER");
    }

    public abstract void configure(HttpSecurity http) throws Exception;


    protected UsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter()
            throws Exception {
        UsernamePasswordAuthenticationFilter filter = new JsonUserNameAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return filter;
    }
}
