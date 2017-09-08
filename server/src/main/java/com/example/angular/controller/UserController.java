package com.example.angular.controller;

import com.example.angular.model.Message;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nlbang on 8/29/2017.
 */
@RestController
@RequestMapping("api/users")
public class UserController {

    @GetMapping
    public Object getUser(Authentication authentication) {
        return authentication.getPrincipal();
    }

    @GetMapping("isLogin")
    public Message checkLogin() {
        return new Message("Success");
    }
}
