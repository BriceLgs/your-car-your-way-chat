package com.ycyw.poc_chat.repository;

import com.ycyw.poc_chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByUtilisateurIdOrderByDateAsc(Integer utilisateurId);
}


