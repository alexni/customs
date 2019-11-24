package ru.customs.dto;

public class DeleteUserRequest {
    Long id;

    public DeleteUserRequest() {
    }

    public DeleteUserRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
