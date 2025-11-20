package com.example.health_platform.modules.chat.DTO;

import lombok.Data;

@Data
public class CreateMessageDTO {
    private Long conversationId;
    private Long senderId;
    private String message;
}
