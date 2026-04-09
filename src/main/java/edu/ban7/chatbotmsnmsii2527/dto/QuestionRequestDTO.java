package edu.ban7.chatbotmsnmsii2527.dto;

import lombok.Data;

import java.util.List;
public record QuestionRequestDTO (String content, List<Integer> includeTagIds, List<Integer> excludeTagIds
){}
