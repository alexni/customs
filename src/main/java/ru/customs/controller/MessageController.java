package ru.customs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.customs.dto.*;
import ru.customs.entity.*;
import ru.customs.exception.UserNotFoundException;
import ru.customs.service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class MessageController {
    Logger log = Logger.getLogger(MessageController.class.toString());

    @Autowired
    MessageService service;

    @Autowired
    FCMTokenService fcmService;

    @Autowired
    UserService userService;

    @Autowired
    DeclarantService declarantService;

    @Autowired
    ClaimService claimService;


    @GetMapping("/claim/{id}/messages")
    Iterable<MessageResponse> getMessages(@PathVariable Long id){
        ClaimResponse claim = claimService.getById(id);

        Iterable<MessageEntity> result = service.getAllMessagesByDeclarant(claim.getDeclarant().getId());
        List<MessageResponse> ret = new ArrayList<>();
        Map<Long, UserEntity> managers = new HashMap<>();
        for (MessageEntity entity: result){
            if (!(entity.getManagerId() == 0) && (managers.get(entity.getManagerId()) == null)){
                try {
                    managers.put(entity.getManagerId(), userService.get(entity.getManagerId()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ret.add(new MessageResponse(entity, entity.getManagerId()==0?null:new UserResponse(managers.get(entity.getManagerId()))));
        }
        return ret;
    }

    @PostMapping("/claim/{id}/message")
    void createMessage(@PathVariable Long id, @RequestBody NewMessageRequest message,
                       @RequestHeader(name = "x-auth-token", required = false, defaultValue = "driver") String token){
        boolean isDriverMessage = token.equals("driver");
        UserEntity manager = null;
        System.out.println("TOKEN: "+token);
        if (!isDriverMessage){
           manager = userService.getUserByLogin(token);
        }
        if (!isDriverMessage && manager == null ) throw new UserNotFoundException();
        ClaimResponse claim = claimService.getById(id);
        MessageEntity entity = new MessageEntity(message.getText(),(isDriverMessage?0:manager.getId()), claim.getId(), claim.getDeclarant().getId(), MessageState.New);
        service.saveToClaim(entity);
        service.send(entity, manager);
    }

    @PostMapping("/phone/message")
    void createPhoneMessage(@RequestBody NewMessageRequest message,
                       @RequestHeader(name = "x-auth-token", required = false, defaultValue = "driver") String token){
        log.info("Phone message "+token+ " "+ message.getText());

        DeclarantEntity declarant = declarantService.getFirebaseId(token);
        if (declarant == null){
            declarant = declarantService.save(new DeclarantEntity(null, null, null, null, null, null, null, token, DeclarantState.ACTIVE, token));
        }
        MessageEntity entity = new MessageEntity(message.getText(),0L, null, declarant.getId(), MessageState.New);
        service.saveToLastClaim(entity, declarant.getId());
    }

    @PostMapping("/phone/token")
    void createPhoneMessage(@RequestBody NewTokenRequest message,
                            @RequestHeader(name = "x-auth-token", required = false, defaultValue = "driver") String token){
        log.info("Phone message "+token+ " FCM token: "+ message.getToken());


        FCMTokenEntity entity = new FCMTokenEntity(token, message.getToken());
        fcmService.save(entity);
    }
}
