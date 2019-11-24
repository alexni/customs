package ru.customs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.customs.dto.*;
import ru.customs.entity.UserEntity;
import ru.customs.repository.UserRepository;

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {
    private Cipher ecipher;
    private Cipher dcipher;
    private SecretKey key;

    @Autowired
    private UserRepository repository;

    private UserService() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        key = KeyGenerator.getInstance("DES").generateKey();
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }

    public String encrypt(String str){

        try {
            byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);
            byte[] enc = ecipher.doFinal(utf8);
            enc = Base64.getEncoder().encode(enc);
            return new String(enc, StandardCharsets.UTF_8);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String decrypt(String str) {
        try {
            byte[] dec =Base64.getDecoder().decode(str.getBytes());
            byte[] utf8 = dcipher.doFinal(dec);
            return new String(utf8, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public UserEntity get(Long id) {
        Optional<UserEntity> ret = repository.findById(id);
        return ret.isPresent()?ret.get():null;
    }


    public UserEntity create(NewUserRequest user) {
        return repository.save(new UserEntity(user));
    }

    public Iterable<UserEntity> getAll() {
        return repository.findAll();
    }

    public UserEntity update(Long id, UpdateUserRequest user) {
        UserEntity old = repository.findById(id).get();
        return repository.save( new UserEntity(id, user, old));
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public UserEntity updateState(UpdateUserStateRequest state) {
        UserEntity entity = repository.findById(state.getId()).get();
        entity.setState(state.getState());
        return repository.save(entity);
    }

    public UserEntity auth(AuthRequest authRequest) {
        return repository.findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
    }

    public UserEntity getUserByLogin(String token) {
        String login = decrypt(token);
        return repository.findByLogin(login);
    }

    public void updatePassword(Long id, UpdatePasswordRequest password) {
        repository.updatePassword(id, password.getPassword());
    }
}
