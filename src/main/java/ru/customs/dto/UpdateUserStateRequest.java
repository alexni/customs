package ru.customs.dto;

import ru.customs.entity.UserState;

public class UpdateUserStateRequest {
    Long id;
    UserState state;

    public UpdateUserStateRequest(Long id, UserState state) {
        this.id = id;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public UserState getState() {
        return state;
    }
}
