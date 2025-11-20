package com.example.health_platform.modules.chat.service;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.auth.repository.UserRepository;
import com.example.health_platform.modules.chat.DTO.ChatConversationResponseDTO;
import com.example.health_platform.modules.chat.DTO.ChatMessageResponseDTO;
import com.example.health_platform.modules.chat.DTO.CreateMessageDTO;
import com.example.health_platform.modules.chat.model.ChatConversation;
import com.example.health_platform.modules.chat.model.ChatMessage;
import com.example.health_platform.modules.chat.repository.ChatConversationRepository;
import com.example.health_platform.modules.chat.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatConversationRepository conversationRepository;
    private final ChatMessageRepository messageRepository;
    private final UserRepository userRepository;

    public ChatServiceImpl(ChatConversationRepository conversationRepository,
                           ChatMessageRepository messageRepository,
                           UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ChatConversationResponseDTO startChat(List<User> participants) {
        ChatConversation conversation = new ChatConversation();
        conversation.setParticipants(participants);
        ChatConversation saved = conversationRepository.save(conversation);

        ChatConversationResponseDTO dto = new ChatConversationResponseDTO();
        dto.setId(saved.getId());
        dto.setParticipantIds(saved.getParticipants().stream().map(User::getId).toList());
        dto.setCreatedAt(saved.getCreatedAt());
        return dto;
    }

    @Override
    public ChatMessageResponseDTO sendMessage(CreateMessageDTO dto) {
        ChatConversation conversation = conversationRepository.findById(dto.getConversationId())
                .orElseThrow(() -> new RuntimeException("Conversation not found"));
        User sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        ChatMessage message = new ChatMessage();
        message.setConversation(conversation);
        message.setSender(sender);
        message.setMessage(dto.getMessage());

        ChatMessage saved = messageRepository.save(message);

        ChatMessageResponseDTO response = new ChatMessageResponseDTO();
        response.setId(saved.getId());
        response.setConversationId(saved.getConversation().getId());
        response.setSenderId(saved.getSender().getId());
        response.setMessage(saved.getMessage());
        response.setSentAt(saved.getSentAt());
        return response;
    }

    @Override
    public List<ChatConversationResponseDTO> getUserConversations(User user) {
        return conversationRepository.findByParticipantsContaining(user)
                .stream()
                .map(conversation -> {
                    ChatConversationResponseDTO dto = new ChatConversationResponseDTO();
                    dto.setId(conversation.getId());
                    dto.setParticipantIds(conversation.getParticipants().stream().map(User::getId).toList());
                    dto.setCreatedAt(conversation.getCreatedAt());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatMessageResponseDTO> getMessages(Long conversationId) {
        ChatConversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        return messageRepository.findByConversationOrderBySentAtAsc(conversation)
                .stream()
                .map(message -> {
                    ChatMessageResponseDTO dto = new ChatMessageResponseDTO();
                    dto.setId(message.getId());
                    dto.setConversationId(conversationId);
                    dto.setSenderId(message.getSender().getId());
                    dto.setMessage(message.getMessage());
                    dto.setSentAt(message.getSentAt());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
