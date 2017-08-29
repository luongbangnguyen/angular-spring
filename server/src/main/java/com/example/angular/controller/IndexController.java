package com.example.angular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nlbang on 8/29/2017.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String indexPage() {
        return "index";
    }
}
