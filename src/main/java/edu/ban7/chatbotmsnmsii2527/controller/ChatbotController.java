package edu.ban7.chatbotmsnmsii2527.controller;

import edu.ban7.chatbotmsnmsii2527.dto.QuestionRequestDTO;
import edu.ban7.chatbotmsnmsii2527.model.Question;
import edu.ban7.chatbotmsnmsii2527.security.AppUserDetails;
import edu.ban7.chatbotmsnmsii2527.security.IsAdmin;
import edu.ban7.chatbotmsnmsii2527.security.IsUser;
import edu.ban7.chatbotmsnmsii2527.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatbotController {

    protected final AiService aiService;

    @PostMapping("/ask")
    @IsUser
    public ResponseEntity<String> ask(
            @AuthenticationPrincipal AppUserDetails userDetails,
            @RequestBody QuestionRequestDTO question) {

        System.out.println(userDetails.getUser().getPseudo());

        return new ResponseEntity<>(
                aiService.askGemini(question, userDetails.getUser()),
                HttpStatus.OK);
    }

    @GetMapping("/history")
    @IsUser
    public ResponseEntity<List<Question>> history(
            @AuthenticationPrincipal AppUserDetails userDetails
    ){
        return new ResponseEntity<>(
                aiService.getHistory(userDetails.getUser()),
                HttpStatus.OK);
    }

    @GetMapping("/history/{userId}")
    @IsAdmin
    public ResponseEntity<List<Question>> userHistory(
            @AuthenticationPrincipal AppUserDetails userDetails,
            @PathVariable Integer userId){

        return new ResponseEntity<>(
                aiService.getHistoryForUser(userId),
                HttpStatus.OK);
    }



}
