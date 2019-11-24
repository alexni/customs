package ru.customs.dto;

import ru.customs.entity.MessageState;

public class NewMessageRequest {
    private String text;

    public NewMessageRequest(String text) {
        this.text = text;
    }

    public NewMessageRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
