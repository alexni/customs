package ru.customs.dto;

import ru.customs.entity.Role;
import ru.customs.entity.UserState;

public class UpdateUserRequest {
    private  Long id;
    private String login;
    private String pass;
    private Role role;
    private String name;
    private String email;
    private UserState state;

    public UpdateUserRequest(Long id, String login, String pass, Role role, String name, String email, UserState state) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.role = role;
        this.name = name;
        this.email = email;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserState getState() {
        return state;
    }
}
