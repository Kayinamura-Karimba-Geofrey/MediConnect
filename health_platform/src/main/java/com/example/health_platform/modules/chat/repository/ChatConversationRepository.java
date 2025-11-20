package com.example.health_platform.modules.chat.repository;

import com.example.health_platform.modules.chat.model.ChatConversation;
import com.example.health_platform.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatConversationRepository extends JpaRepository<ChatConversation, Long> {
    List<ChatConversation> findByParticipantsContaining(User user);
}
