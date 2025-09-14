package com.ycyw.poc_chat.controller;

import com.ycyw.poc_chat.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.send")
    @SendTo("/topic/chat")
    public Message sendMessage(Message message) {
        // Réponse automatique simple
        Message agentResponse = new Message();
        agentResponse.setUtilisateurId(message.getUtilisateurId());
        agentResponse.setContenu("Agent: J'ai reçu votre message - " + message.getContenu() + ". Comment puis-je vous aider ?");
        agentResponse.setTypeContact("chat");
        return agentResponse;
    }
}