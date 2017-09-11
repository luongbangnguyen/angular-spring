package com.example.angular.config.web.prod;

import com.example.angular.config.web.MvcConfigBase;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by nlbang on 8/30/2017.
 */
@Configuration
@Profile("prod")
public class MvcConfigProd extends MvcConfigBase{}
