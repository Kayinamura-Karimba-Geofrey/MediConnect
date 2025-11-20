package com.example.health_platform.modules.chat.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.chat.DTO.ChatConversationResponseDTO;
import com.example.health_platform.modules.chat.DTO.ChatMessageResponseDTO;
import com.example.health_platform.modules.chat.DTO.CreateMessageDTO;

import java.util.List;

public interface ChatService {
    ChatConversationResponseDTO startChat(List<User> participants);
    ChatMessageResponseDTO sendMessage(CreateMessageDTO dto);
    List<ChatConversationResponseDTO> getUserConversations(User user);
    List<ChatMessageResponseDTO> getMessages(Long conversationId);
}
