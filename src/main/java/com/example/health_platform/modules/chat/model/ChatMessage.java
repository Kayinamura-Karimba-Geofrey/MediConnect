package com.example.health_platform.modules.chat.model;



import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private ChatConversation conversation;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    private String message;

    private LocalDateTime sentAt = LocalDateTime.now();
}
