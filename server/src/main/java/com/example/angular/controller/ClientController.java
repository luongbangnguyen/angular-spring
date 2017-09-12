package com.example.angular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by bangnl on 8/30/17.
 */
@Controller
public class ClientController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "index";
    }

    @GetMapping("websocket")
    public String websocket() {
        return "index";
    }

    @GetMapping("calendar")
    public String calendar() {
        return "index";
    }
}
