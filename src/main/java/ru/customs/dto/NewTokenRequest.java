package ru.customs.dto;

public class NewTokenRequest {
    private String token;

    public NewTokenRequest(String token) {
        this.token = token;
    }

    public NewTokenRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
