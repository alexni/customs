package ru.customs.entity;

import ru.customs.dto.NewUserRequest;
import ru.customs.dto.UpdateUserRequest;

import javax.persistence.*;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String login;
    private String pass;
    private Role role;
    private String name;
    private String email;
    private UserState state;

    public UserEntity() {
    }

    public UserEntity(Long id, UpdateUserRequest user, UserEntity old) {
        this.id = id;
        this.login = user.getLogin()==null?old.getLogin():user.getLogin();
        this.pass = old.getPass();
        this.role = user.getRole()==null?old.getRole():user.getRole();
        this.name = user.getName()==null?old.getName():user.getName();
        this.email = user.getEmail()==null?old.getEmail():user.getEmail();
        this.state = user.getState()==null?old.getState():user.getState();
    }

    public UserEntity(Long id, String login, String pass, Role role, String name, String email, UserState state) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.role = role;
        this.name = name;
        this.email = email;
        this.state = state;
    }

    public UserEntity(NewUserRequest user) {
        this.login = user.getLogin();
        this.pass = user.getPass();
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
