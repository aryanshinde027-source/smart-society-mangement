
package com.test.Services;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.Dto.AiChatRequest;
import com.test.Dto.AiChatResponse;
import com.test.Entities.AiChatMessage;
import com.test.Repos.AiChatMessageRepo;

@Service
public class AiAssistantService {

    private final AiChatMessageRepo repo;
    private final GeminiAiService gemini;

    public AiAssistantService(
            AiChatMessageRepo repo,
            GeminiAiService gemini) {
        this.repo = repo;
        this.gemini = gemini;
    }

    @Transactional
    public AiChatResponse chat(AiChatRequest request) {

        if (request.getResidentId() == null
                || request.getResidentId() <= 0) {
            throw new IllegalArgumentException(
                    "residentId must be a positive number.");
        }

        String message = request.getMessage() == null
                ? "" : request.getMessage().trim();

        if (message.isBlank() || message.length() > 2000) {
            throw new IllegalArgumentException(
                    "Message must contain 1 to 2000 characters.");
        }

        String conversationId = request.getConversationId();

        if (conversationId == null || conversationId.isBlank()) {
            conversationId = UUID.randomUUID().toString();
        } else {
            conversationId = conversationId.trim();
        }

        if (conversationId.length() > 36) {
            throw new IllegalArgumentException(
                    "conversationId must not exceed 36 characters.");
        }

        List<AiChatMessage> history =
                repo.findTop20ByConversationIdAndResidentIdOrderByCreatedAtDesc(
                        conversationId, request.getResidentId());

        Collections.reverse(history);

        StringBuilder prompt = new StringBuilder();

        prompt.append(
                "You are a helpful assistant for a housing society app. ")
              .append("Answer politely and concisely about complaints, ")
              .append("notices, maintenance, facilities, visitors and bookings. ")
              .append("Do not claim to know actual payment, booking or account ")
              .append("status unless verified information is provided. ")
              .append("If uncertain, ask the resident to check with society ")
              .append("management. Never ask for passwords or OTPs. ")
              .append("For immediate danger, advise contacting local emergency ")
              .append("services or society security.\n\n");

        for (AiChatMessage item : history) {
            String speaker = "assistant".equals(item.getRole())
                    ? "Assistant" : "Resident";

            prompt.append(speaker)
                  .append(": ")
                  .append(item.getContent())
                  .append("\n");
        }

        prompt.append("Resident: ")
              .append(message)
              .append("\nAssistant:");

        AiChatMessage userMessage = new AiChatMessage();
        userMessage.setConversationId(conversationId);
        userMessage.setResidentId(request.getResidentId());
        userMessage.setRole("user");
        userMessage.setContent(message);

        repo.save(userMessage);

        String answer = gemini.generate(prompt.toString());

        AiChatMessage assistantMessage = new AiChatMessage();
        assistantMessage.setConversationId(conversationId);
        assistantMessage.setResidentId(request.getResidentId());
        assistantMessage.setRole("assistant");
        assistantMessage.setContent(answer);

        assistantMessage = repo.save(assistantMessage);

        return new AiChatResponse(
                conversationId,
                request.getResidentId(),
                assistantMessage.getRole(),
                assistantMessage.getContent(),
                assistantMessage.getCreatedAt());
    }

    public List<AiChatResponse> history(
            String conversationId, Long residentId) {

        if (residentId == null || residentId <= 0) {
            throw new IllegalArgumentException(
                    "residentId must be a positive number.");
        }

        return repo
                .findByConversationIdAndResidentIdOrderByCreatedAtAsc(
                        conversationId, residentId)
                .stream()
                .map(item -> new AiChatResponse(
                        item.getConversationId(),
                        item.getResidentId(),
                        item.getRole(),
                        item.getContent(),
                        item.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
