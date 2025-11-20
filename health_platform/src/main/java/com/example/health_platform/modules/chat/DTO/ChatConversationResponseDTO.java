package com.example.health_platform.modules.chat.DTO;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChatConversationResponseDTO {
    private Long id;
    private List<Long> participantIds;
    private LocalDateTime createdAt;
}
