package ru.customs.dto;

import ru.customs.entity.DeclarantState;

public class DeclarantStateRequest {
    private DeclarantState state;

    public DeclarantStateRequest() {
    }

    public DeclarantStateRequest(DeclarantState state) {
        this.state = state;
    }

    public DeclarantState getState() {
        return state;
    }

    public void setState(DeclarantState state) {
        this.state = state;
    }
}
