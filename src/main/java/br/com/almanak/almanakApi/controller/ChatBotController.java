package br.com.almanak.almanakApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/chatbot")
public class ChatBotController {

    @GetMapping
    public Boolean index(){
        return true;
    }
    
}
