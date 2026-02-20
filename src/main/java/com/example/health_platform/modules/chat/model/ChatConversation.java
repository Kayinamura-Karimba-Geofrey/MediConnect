package com.example.health_platform.modules.chat.model;

import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class ChatConversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "conversation_users",
        joinColumns = @JoinColumn(name = "conversation_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants;

    private LocalDateTime createdAt = LocalDateTime.now();
}
