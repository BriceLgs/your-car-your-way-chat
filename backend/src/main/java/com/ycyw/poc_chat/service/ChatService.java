package com.ycyw.poc_chat.service;

import com.ycyw.poc_chat.entity.Message;
import com.ycyw.poc_chat.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final MessageRepository messageRepository;

    public ChatService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> findConversation(Integer utilisateurId) {
        return messageRepository.findByUtilisateurIdOrderByDateAsc(utilisateurId);
    }
}


