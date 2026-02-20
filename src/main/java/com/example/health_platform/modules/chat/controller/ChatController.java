package com.example.health_platform.modules.chat.controller;

import com.example.health_platform.auth.model.User;
import com.example.health_platform.modules.chat.DTO.ChatConversationResponseDTO;
import com.example.health_platform.modules.chat.DTO.ChatMessageResponseDTO;
import com.example.health_platform.modules.chat.DTO.CreateMessageDTO;
import com.example.health_platform.modules.chat.service.ChatService;
import com.example.health_platform.auth.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;
    private final UserRepository userRepository;

    public ChatController(ChatService chatService, UserRepository userRepository) {
        this.chatService = chatService;
        this.userRepository = userRepository;
    }

    @PostMapping("/start")
    public ChatConversationResponseDTO startChat(@RequestBody List<Long> participantIds) {
        List<User> participants = participantIds.stream()
                .map(id -> userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found")))
                .toList();
        return chatService.startChat(participants);
    }

    @PostMapping("/send-message")
    public ChatMessageResponseDTO sendMessage(@RequestBody CreateMessageDTO dto) {
        return chatService.sendMessage(dto);
    }

    @GetMapping("/conversations")
    public List<ChatConversationResponseDTO> getUserConversations(@RequestParam Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return chatService.getUserConversations(user);
    }

    @GetMapping("/messages/{conversationId}")
    public List<ChatMessageResponseDTO> getMessages(@PathVariable Long conversationId) {
        return chatService.getMessages(conversationId);
    }
}
