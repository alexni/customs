package ru.customs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.customs.dto.DeclarantResponse;
import ru.customs.dto.DeclarantStateRequest;
import ru.customs.dto.DeclarantsResponse;
import ru.customs.entity.DeclarantEntity;
import ru.customs.service.DeclarantService;

import java.util.List;

@RestController
public class DeclarantController {

    @Autowired
    private DeclarantService service;

    @GetMapping("/declarants/list")
    public DeclarantsResponse getDeclarant(@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                                           @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset){
        return service.getDeclarants(limit, offset);
    }

    @PostMapping("/declarant/{id}/state")
    public void setState(@PathVariable Long id, @RequestBody DeclarantStateRequest state){
        service.setState(id, state);
    }

    @GetMapping("/phone/declarant")
    public DeclarantResponse getDeclarant(@RequestHeader(name = "x-auth-token", required = true) String token){
        DeclarantEntity declarant = service.getFirebaseId(token);
        if (declarant!=null) {
            return new DeclarantResponse(declarant);
        }
        return new DeclarantResponse();
    }

}
