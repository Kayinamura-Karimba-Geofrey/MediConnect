package com.example.health_platform.modules.chat.DTO;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ChatMessageResponseDTO {
    private Long id;
    private Long conversationId;
    private Long senderId;
    private String message;
    private LocalDateTime sentAt;
}
