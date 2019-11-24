package ru.customs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.customs.entity.Role;
import ru.customs.entity.UserEntity;
import ru.customs.entity.UserState;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private Long id;
    private String login;
    private Role role;
    private String name;
    private String email;
    private UserState state;

    public UserResponse() {
    }



    public UserResponse(UserEntity user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.role = user.getRole();
        this.name = user.getName();
        this.email = user.getEmail();
        this.state = user.getState();
    }


    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setState(UserState state) {
        this.state = state;
    }
}
