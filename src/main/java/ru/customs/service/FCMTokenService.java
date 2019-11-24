package ru.customs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.customs.dto.ClaimResponse;
import ru.customs.entity.*;
import ru.customs.repository.FCMTokenRepository;
import ru.customs.repository.MesageRepository;

import java.util.logging.Logger;

@Service
public class FCMTokenService {
    Logger log = Logger.getLogger(FCMTokenService.class.toString());

    @Autowired
    FCMTokenRepository repository;

    public void save(FCMTokenEntity message) {
        System.out.println("Save to fcmtoken message "+message.getUserToken() + " text: "+message.getFCMToken());
        FCMTokenEntity tokenEntity= repository.findByUser(message.getUserToken());
        String FCMToken = tokenEntity==null?null:tokenEntity.getFCMToken();
        try {

            if (FCMToken != null && FCMToken.equals(message.getFCMToken())) {

            } else if(FCMToken==null){
                repository.save(message);
            } else {
                repository.update(message.getUserToken(), message.getFCMToken());
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            repository.save(message);
        }
    }


    public String getByDeclarant(String firebase) {
        FCMTokenEntity tokenEntity = repository.findByUser(firebase);
        if (tokenEntity==null) return null;
        else return tokenEntity.getFCMToken();
    }
}
