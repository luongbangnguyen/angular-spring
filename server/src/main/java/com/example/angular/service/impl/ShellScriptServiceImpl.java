package com.example.angular.service.impl;

import com.example.angular.model.Greeting;
import com.example.angular.service.ShellScriptService;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by nlbang on 9/6/2017.
 */
@Service
public class ShellScriptServiceImpl implements ShellScriptService{

    private final SimpMessagingTemplate template;

    @Autowired
    ShellScriptServiceImpl(SimpMessagingTemplate template){
        this.template = template;
    }

    @Override
    public void executeCommand(String destination) {
        try {
            String command = "ping -c 20 google.com";
            if(SystemUtils.IS_OS_WINDOWS) {
                command = "ping -n 20 google.com";
            }
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = reader.readLine())!= null) {
                template.convertAndSend("/queue/greetings/" + destination, new Greeting(line, true));
            }
            template.convertAndSend("/queue/greetings/" + destination, new Greeting("", false));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
