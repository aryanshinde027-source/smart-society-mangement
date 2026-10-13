
package com.test.Dto;

import java.time.LocalDateTime;

public class AiChatResponse {

    private String conversationId;
    private Integer residentId;
    private String role;
    private String message;
    private LocalDateTime createdAt;

    public AiChatResponse(
            String conversationId,
            Integer residentId,
            String role,
            String message,
            LocalDateTime createdAt) {

        this.conversationId = conversationId;
        this.residentId = residentId;
        this.role = role;
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getConversationId() {
        return conversationId;
    }

    public Integer getResidentId() {
        return residentId;
    }

    public String getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

