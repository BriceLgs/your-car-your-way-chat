package com.ycyw.poc_chat.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "utilisateur_id")
    private Integer utilisateurId;

    @Column(name = "type_contact")
    private String typeContact = "chat";

    @Column(name = "contenu")
    private String contenu;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(Integer utilisateurId) { this.utilisateurId = utilisateurId; }
    public String getTypeContact() { return typeContact; }
    public void setTypeContact(String typeContact) { this.typeContact = typeContact; }
    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}