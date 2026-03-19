package com.example.health_platform.modules.chat.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.chat.dto.ChatConversationResponseDTO;
import com.example.health_platform.modules.chat.dto.ChatMessageResponseDTO;
import com.example.health_platform.modules.chat.dto.CreateMessageDTO;

import java.util.List;

public interface ChatService {
    ChatConversationResponseDTO startChat(List<User> participants);
    ChatMessageResponseDTO sendMessage(CreateMessageDTO dto);
    List<ChatConversationResponseDTO> getUserConversations(User user);
    List<ChatMessageResponseDTO> getMessages(Long conversationId);
}
