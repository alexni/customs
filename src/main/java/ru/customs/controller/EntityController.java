package ru.customs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntityController {

    @RequestMapping("/")
    public String index(){
        return "Customs application";
    }


}
