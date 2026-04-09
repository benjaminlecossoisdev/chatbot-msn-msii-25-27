package edu.ban7.chatbotmsnmsii2527.service;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import edu.ban7.chatbotmsnmsii2527.dao.QuestionDao;
import edu.ban7.chatbotmsnmsii2527.dao.RecipeDao;
import edu.ban7.chatbotmsnmsii2527.dao.TagDao;
import edu.ban7.chatbotmsnmsii2527.dto.QuestionRequestDTO;
import edu.ban7.chatbotmsnmsii2527.model.AppUser;
import edu.ban7.chatbotmsnmsii2527.model.Question;
import edu.ban7.chatbotmsnmsii2527.model.Recipe;
import edu.ban7.chatbotmsnmsii2527.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AiService {

    protected final Client client;
    protected final RecipeDao recipeDao;
    protected final TagDao tagDao;
    protected final QuestionDao questionDao;


    protected String callGemini(String prompt) {
        Content content = Content.builder()
                .role("user")
                .parts(List.of(Part.builder()
                                .text(prompt)
                                .build()))
                .build();

        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash-lite", content , null);
        return response.text();
    }

    public String askGemini(QuestionRequestDTO dto, AppUser user) {

        List<Tag> includeTags = dto.includeTagIds() == null ? List.of()
                : tagDao.findAllById(dto.includeTagIds());
        List<Tag> excludeTags = dto.excludeTagIds() == null ? List.of()
                : tagDao.findAllById(dto.excludeTagIds());


        List<Recipe> recipes = recipeDao.findAll().stream()
                .filter(r -> includeTags.isEmpty() || r.getTags().containsAll(includeTags))
                .filter(r -> excludeTags.isEmpty() || r.getTags().stream().noneMatch(excludeTags::contains))
                .collect(Collectors.toList());


        String formattedRecipes = recipes.stream()
                .map(r -> {
                    String tagsFormatted = r.getTags().stream()
                            .map(t -> t.getName())
                            .collect(Collectors.joining(","));
                    return r.getName() + "(" + tagsFormatted + ")";
                })
                .collect(Collectors.joining(" -"));

        String finalPrompt = "Parmi les recettes listées, trouve celle correspondant le plus à cette demande, " +
                "et répond comme si tu étais un serveur de restaurant '" +
                dto.content() + "' recettes : " + formattedRecipes;

        String aiResponse = callGemini(finalPrompt);

        questionDao.save(Question.builder()
                .content(dto.content())
                .user(user)
                .includeTags(includeTags)
                .excludeTags(excludeTags)
                .build()
        );

        return aiResponse;
    }

    public List<Question> getHistory(AppUser user){
            return questionDao.findByUserId(user.getId());
    }

    public List<Question> getHistoryForUser(Integer userId) {
        return questionDao.findByUserId(userId);
    }

}
