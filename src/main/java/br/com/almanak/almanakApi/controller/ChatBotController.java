package br.com.almanak.almanakApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatbot")
public class ChatBotController {

    @GetMapping
    public String index(){
        return "ok";
    }
    
}
