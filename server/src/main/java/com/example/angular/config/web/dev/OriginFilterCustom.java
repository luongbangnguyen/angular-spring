package com.example.angular.config.web.dev;

import com.example.angular.constants.PropertiesKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nlbang on 8/31/2017.
 */
@Configuration
@Profile("dev")
public class OriginFilterCustom extends OncePerRequestFilter {

    @Value("${" + PropertiesKey.ADDRESS_ORIGIN_ACCESS + "}")
    private String addressOriginAccess;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String origin = httpServletRequest.getHeader("origin");
        if (StringUtils.equals(origin, this.addressOriginAccess)) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin", addressOriginAccess);
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
