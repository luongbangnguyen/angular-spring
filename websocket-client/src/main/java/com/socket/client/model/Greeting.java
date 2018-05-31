package com.socket.client.model;

public class Greeting {
    private String content;
    private Boolean isProcessing;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getProcessing() {
        return isProcessing;
    }

    public void setProcessing(Boolean processing) {
        isProcessing = processing;
    }
}