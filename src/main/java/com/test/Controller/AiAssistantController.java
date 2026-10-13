
package com.test.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.test.Dto.AiChatRequest;
import com.test.Dto.AiChatResponse;
import com.test.Dto.ComplaintSuggestionRequest;
import com.test.Dto.ComplaintSuggestionResponse;
import com.test.Services.AiAssistantService;
import com.test.Services.GeminiAiService;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*")
public class AiAssistantController {

    private final AiAssistantService service;
    private final GeminiAiService gemini;

    public AiAssistantController(
            AiAssistantService service,
            GeminiAiService gemini) {
        this.service = service;
        this.gemini = gemini;
    }

    @PostMapping("/chat")
    public AiChatResponse chat(@RequestBody AiChatRequest request) {
        try {
            return service.chat(request);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY, e.getMessage());
        }
    }

  
   
    @GetMapping("/history/{conversationId}")
    public ResponseEntity<?> getChatHistory(
            @PathVariable String conversationId,
            @RequestParam Long residentId) {

        return ResponseEntity.ok(
                service.history(conversationId, residentId));
    }
   
   

    @PostMapping("/complaint-suggestion")
    public ComplaintSuggestionResponse suggestComplaint(
            @RequestBody ComplaintSuggestionRequest request) {

        String description = request.getDescription();

        if (description == null || description.isBlank()
                || description.length() > 2000) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Description must contain 1 to 2000 characters.");
        }

        try {
            return gemini.suggestComplaint(description.trim());
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY, e.getMessage());
        }
    }
}

