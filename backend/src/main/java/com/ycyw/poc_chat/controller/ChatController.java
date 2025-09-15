package com.ycyw.poc_chat.controller;

import com.ycyw.poc_chat.entity.Message;
import com.ycyw.poc_chat.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/chat")
    public Message sendMessage(Message message) {
        chatService.save(message);
        return message;
    }
}