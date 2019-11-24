package ru.customs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.customs.dto.*;
import ru.customs.entity.Role;
import ru.customs.entity.UserEntity;
import ru.customs.entity.UserState;
import ru.customs.exception.UserNotFoundException;
import ru.customs.service.UserService;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletResponse;
import java.nio.channels.spi.SelectorProvider;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user")
    public UserResponse getUser(@RequestHeader("x-auth-token") String token){
        UserEntity entity = service.getUserByLogin(token);
        if (entity == null) throw new UserNotFoundException();
        return new UserResponse(entity);
    }

    @PostMapping("/auth")
    public UserResponse auth(@RequestBody AuthRequest auth, HttpServletResponse response) throws NoSuchAlgorithmException {
        UserEntity user = service.auth(auth);
        if (user == null) throw new UserNotFoundException();
        if (user.getLogin().equals(auth.getLogin()) && user.getPass().equals(auth.getPassword())) {
            response.setHeader("x-auth-token", service.encrypt(auth.getLogin()));
            return new UserResponse(user);
        }
        throw new UserNotFoundException();
    }

    @GetMapping("/admin/user/{id}")
    public UserResponse getUser(@PathVariable Long id){
        return new UserResponse(service.get(id));
    }

    @PostMapping("/admin/user")
    public UserResponse createUser(@RequestBody NewUserRequest user){
        UserEntity ret = service.create(user);
        return new UserResponse(ret);
    }

    @GetMapping("/admin/users")
    public Iterable<UserResponse> getUser(){
        ArrayList<UserResponse> ret = new ArrayList<>();
        Iterable<UserEntity> iterable = service.getAll();
        for (UserEntity entity : iterable){
            ret.add(new UserResponse(entity));
        }
        return ret;
    }

    @PutMapping("/admin/user/{id}")
    public UserResponse updateUser(@RequestBody UpdateUserRequest user, @PathVariable Long id){
        return new UserResponse(service.update(id, user));
    }

    @PutMapping("/admin/user/{id}/password")
    public void updateUser(@RequestBody UpdatePasswordRequest password, @PathVariable Long id){
        service.updatePassword(id, password);
    }

    @DeleteMapping("/admin/user")
    public void deleteUser(@RequestBody DeleteUserRequest user){
        service.deleteUser(user.getId());
    }

    @PutMapping("/admin/user/state")
    public UserResponse updateState(@RequestBody UpdateUserStateRequest state){
        return new UserResponse(service.updateState(state));
    }
}
