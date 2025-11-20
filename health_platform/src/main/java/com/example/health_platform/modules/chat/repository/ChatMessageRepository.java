package com.example.health_platform.modules.chat.repository;

import com.example.health_platform.modules.chat.model.ChatMessage;
import com.example.health_platform.modules.chat.model.ChatConversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByConversationOrderBySentAtAsc(ChatConversation conversation);
}
