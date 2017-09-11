package com.example.angular.config.security.prod;

import com.example.angular.config.security.WebSecurityConfigAbstract;
import com.example.angular.config.web.dev.OriginFilterCustom;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * Created by nlbang on 8/24/2017.
 */
@Configuration
@Profile("prod")
public class WebSecurityConfigProd extends WebSecurityConfigAbstract {

    private final AuthenticationEntryPoint restAuthenticationEntryPoint;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final OriginFilterCustom originFilterCustom;

    public WebSecurityConfigProd(AuthenticationSuccessHandler authenticationSuccessHandler,
                                 AuthenticationFailureHandler authenticationFailureHandler,
                                 AuthenticationEntryPoint restAuthenticationEntryPoint,
                                 LogoutSuccessHandler logoutSuccessHandler,
                                 OriginFilterCustom originFilterCustom) {
        super(authenticationSuccessHandler, authenticationFailureHandler);
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.originFilterCustom = originFilterCustom;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAt(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout().permitAll()
                .logoutSuccessHandler(logoutSuccessHandler);
    }
}
