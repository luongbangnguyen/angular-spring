package com.example.angular.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Greeting {
    private String content;
    private Boolean isProcessing;
}