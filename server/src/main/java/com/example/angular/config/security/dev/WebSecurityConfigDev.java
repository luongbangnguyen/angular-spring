package com.example.angular.config.security.dev;

import com.example.angular.config.security.WebSecurityConfigAbstract;
import com.example.angular.config.web.dev.OriginFilterCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

/**
 * Created by nlbang on 8/24/2017.
 */
@Configuration
@Profile("dev")
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfigDev extends WebSecurityConfigAbstract {


    private final AuthenticationEntryPoint restAuthenticationEntryPoint;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final OriginFilterCustom originFilterCustom;

    @Autowired
    WebSecurityConfigDev(AuthenticationSuccessHandler authenticationSuccessHandler,
                         AuthenticationFailureHandler authenticationFailureHandler,
                         AuthenticationEntryPoint restAuthenticationEntryPoint,
                         LogoutSuccessHandler logoutSuccessHandler,
                         OriginFilterCustom originFilterCustom) {
        super( authenticationSuccessHandler, authenticationFailureHandler);

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
                .authorizeRequests()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .addFilterAfter(originFilterCustom, WebAsyncManagerIntegrationFilter.class);
    }

}
