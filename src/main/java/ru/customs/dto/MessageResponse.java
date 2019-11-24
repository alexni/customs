package ru.customs.dto;

import ru.customs.entity.MessageEntity;
import ru.customs.entity.MessageState;

public class MessageResponse {
    private Long id;
    private String text;
    private UserResponse manager;
    private MessageState state;

    public MessageResponse(Long id, String text, UserResponse manager, MessageState state) {
        this.id = id;
        this.text = text;
        this.manager = manager;
        this.state = state;
    }

    public MessageResponse(MessageEntity entity, UserResponse manager) {
        this.id = entity.getId();
        this.text = entity.getText();
        this.manager = manager;
        this.state = entity.getState();
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public UserResponse getManager() {
        return manager;
    }

    public MessageState getState() {
        return state;
    }
}
