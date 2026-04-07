package edu.ban7.chatbotmsnmsii2527.controller;

import edu.ban7.chatbotmsnmsii2527.dao.TagDao;
import edu.ban7.chatbotmsnmsii2527.dto.Question;
import edu.ban7.chatbotmsnmsii2527.model.Tag;
import edu.ban7.chatbotmsnmsii2527.service.AiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChatbotController {

    protected final AiService aiService;

    @PostMapping("/ask")
    public ResponseEntity<String> create(@RequestBody Question question) {
        return new ResponseEntity<>(
                aiService.askGemini(question.content()),
                HttpStatus.OK);
    }

}
