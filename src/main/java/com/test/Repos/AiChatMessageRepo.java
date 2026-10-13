
package com.test.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Entities.AiChatMessage;

public interface AiChatMessageRepo
        extends JpaRepository<AiChatMessage, Long> {

    List<AiChatMessage>
    findTop20ByConversationIdAndResidentIdOrderByCreatedAtDesc(
            String conversationId, Integer residentId);

    List<AiChatMessage>
    findByConversationIdAndResidentIdOrderByCreatedAtAsc(
            String conversationId, Long residentId);
}
