package ru.customs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.customs.dto.ClaimResponse;
import ru.customs.dto.ClaimsResponse;
import ru.customs.dto.NewClaimRequest;
import ru.customs.dto.UserResponse;
import ru.customs.entity.*;
import ru.customs.service.ClaimService;
import ru.customs.service.DeclarantService;
import ru.customs.service.MessageService;
import ru.customs.service.UserService;

import java.util.Date;
import java.util.HashSet;
import java.util.logging.Logger;

@RestController
public class ClaimController {
    Logger log = Logger.getLogger(ClaimController.class.toString());

    @Autowired
    private ClaimService service;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;


    @GetMapping("/declarant/claim/list_state")
    public ClaimsResponse search(
            @RequestParam(name="state", required = false, defaultValue = "Start") ClaimState state,
            @RequestParam(name="limit" , required = false, defaultValue = "10") Integer limit,
            @RequestParam(name="offset", required = false, defaultValue ="0") Integer offset
    ){
       return service.search(state,  limit, offset);
    }


    @GetMapping("/declarant/claim/list")
    public ClaimsResponse search(
            @RequestParam(name="limit" , required = false, defaultValue = "10") Integer limit,
            @RequestParam(name="offset", required = false, defaultValue ="0") Integer offset,
            @RequestParam(name="manager_id", required = false, defaultValue = "-1") Integer managerId,
            @RequestParam(name="state", required = false, defaultValue = "None") ClaimState state
    ){
        return service.search(managerId, limit, offset, state);
    }





    @PostMapping("/phone/claim")
    public ClaimEntity create(@RequestBody NewClaimRequest claimRequest, @RequestHeader(name = "x-auth-token", required = true) String token){
        String message = "token: "+token+" reques: "+(claimRequest==null?"null":claimRequest.toString());
        log.info(message);
        claimRequest.setState(ClaimState.Start);
        return service.create(claimRequest, token);
    }


    @GetMapping("/declarant/claim/{id}")
    public ClaimResponse get(@PathVariable Long id, @RequestHeader(name = "x-auth-token", required = true) String token){
        UserEntity user = userService.getUserByLogin(token);
        return service.getById(id);
    }

    @PostMapping("/declarant/claim/{id}/state/error")
    public void setError(@PathVariable Long id, @RequestHeader("x-auth-token") String token){
        UserEntity user = userService.getUserByLogin(token);
        service.setState(id, ClaimState.Error, user.getId());
        ClaimResponse claim = service.getById(id);
        MessageEntity message = new MessageEntity("Изменен статус на "+ClaimState.Error.toString(), user.getId(), id, claim.getDeclarant().getId(), MessageState.New);
        messageService.saveToClaim(message);
    }

    @PostMapping("/declarant/claim/{id}/state/success")
    public void setSuccess(@PathVariable Long id, @RequestHeader("x-auth-token") String token){
        UserEntity user = userService.getUserByLogin(token);
        service.setState(id, ClaimState.Success, user.getId());
        ClaimResponse claim = service.getById(id);
        MessageEntity message = new MessageEntity("Изменен статус на "+ClaimState.Success.toString(), user.getId(), id, claim.getDeclarant().getId(), MessageState.New);
        messageService.saveToClaim(message);
    }

    @PostMapping("/declarant/claim/{id}/state/start")
    public void setStart(@PathVariable Long id, @RequestHeader("x-auth-token") String token){
        UserEntity user = userService.getUserByLogin(token);
        service.setState(id, ClaimState.Start, user.getId());
        ClaimResponse claim = service.getById(id);
        MessageEntity message = new MessageEntity("Изменен статус на "+ClaimState.Start.toString(), user.getId(), id, claim.getDeclarant().getId(), MessageState.New);
        messageService.saveToClaim(message);
    }

    @PostMapping("/declarant/claim/{id}/state/reject")
    public void setReject(@PathVariable Long id, @RequestHeader("x-auth-token") String token){
        UserEntity user = userService.getUserByLogin(token);
        service.setState(id, ClaimState.Reject, user.getId());
        ClaimResponse claim = service.getById(id);
        MessageEntity message = new MessageEntity("Изменен статус на "+ClaimState.Reject.toString(), user.getId(), id, claim.getDeclarant().getId(), MessageState.New);
        messageService.saveToClaim(message);
    }

}
