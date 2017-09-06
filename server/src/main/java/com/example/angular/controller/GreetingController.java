package com.example.angular.controller;

import com.example.angular.service.ShellScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    @Autowired
    private ShellScriptService shellScriptService;

    @MessageMapping("/hello/{destination}")
    public void greeting(@DestinationVariable("destination") String  destination) throws Exception {
        shellScriptService.executeCommand(destination);
    }

}