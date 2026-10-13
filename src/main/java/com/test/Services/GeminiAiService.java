
package com.test.Services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.Dto.ComplaintSuggestionResponse;

@Service
public class GeminiAiService {

    private final RestClient client;
    private final ObjectMapper mapper;
    private final String apiKey;
    private final String model;

    public GeminiAiService(
            RestClient.Builder builder,
            ObjectMapper mapper,
            @Value("${gemini.api-key:}") String apiKey,
            @Value("${gemini.model:gemini-3.5-flash-lite}") String model) {

        this.client = builder
                .baseUrl("https://generativelanguage.googleapis.com")
                .build();

        this.mapper = mapper;
        this.apiKey = apiKey;
        this.model = model;
    }

    public String generate(String prompt) {

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "Gemini API key is missing. Set GEMINI_API_KEY.");
        }

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of(
                                "role", "user",
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                ),
                "generationConfig", Map.of(
                        "temperature", 0.4,
                        "maxOutputTokens", 900
                )
        );

        try {
            JsonNode response = client.post()
                    .uri("/v1beta/models/" + model + ":generateContent")
                    .header("x-goog-api-key", apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .body(JsonNode.class);

            JsonNode result = response == null
                    ? null
                    : response.at("/candidates/0/content/parts/0/text");

            if (result == null || result.isMissingNode()
                    || result.asText().isBlank()) {
                throw new IllegalStateException(
                        "Gemini returned no text. Check model access or quota.");
            }

            return result.asText().trim();

        }  catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(
                    "Gemini request failed: " + e.getMessage(), e);
        }
    }

    public ComplaintSuggestionResponse suggestComplaint(String description) {

        String prompt =
                "You help residents write housing society complaints. "
                + "Return ONLY valid JSON with keys title, description, "
                + "category, priority, nextSteps. "
                + "category must be one of Plumbing, Electrical, Cleanliness, "
                + "Security, Noise, Lift, Parking, Maintenance, Other. "
                + "priority must be Low, Medium, High, or Urgent. "
                + "Do not invent facts or exaggerate. "
                + "nextSteps must be an array of 2 or 3 practical steps. "
                + "Priority is only a suggestion. Resident report: "
                + description;

        String raw = generate(prompt)
                .replaceFirst("^```(?:json)?\\s*", "")
                .replaceFirst("\\s*```$", "")
                .trim();

        try {
            ComplaintSuggestionResponse result =
                    mapper.readValue(raw, ComplaintSuggestionResponse.class);

            if (result.getTitle() == null
                    || result.getDescription() == null
                    || result.getCategory() == null
                    || result.getPriority() == null
                    || result.getNextSteps() == null) {

                throw new IllegalStateException(
                        "Gemini returned incomplete complaint suggestions.");
            }

            return result;

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Could not read complaint suggestions. Please try again.",
                    e);
        }
    }
}

