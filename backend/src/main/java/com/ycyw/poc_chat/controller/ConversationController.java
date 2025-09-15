package com.ycyw.poc_chat.controller;

import com.ycyw.poc_chat.entity.Message;
import com.ycyw.poc_chat.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ChatService chatService;

    public ConversationController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{utilisateurId}")
    public ResponseEntity<List<Message>> getConversation(@PathVariable Integer utilisateurId) {
        List<Message> messages = chatService.findConversation(utilisateurId);
        return ResponseEntity.ok(messages);
    }
}


