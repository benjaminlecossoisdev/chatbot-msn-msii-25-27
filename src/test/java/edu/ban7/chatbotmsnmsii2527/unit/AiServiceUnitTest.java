package edu.ban7.chatbotmsnmsii2527.unit;

import com.google.genai.Client;
import edu.ban7.chatbotmsnmsii2527.dao.QuestionDao;
import edu.ban7.chatbotmsnmsii2527.dao.RecipeDao;
import edu.ban7.chatbotmsnmsii2527.dao.TagDao;
import edu.ban7.chatbotmsnmsii2527.model.AppUser;
import edu.ban7.chatbotmsnmsii2527.model.Question;
import edu.ban7.chatbotmsnmsii2527.service.AiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class AiServiceUnitTest {

    @Mock
    private Client client;
    @Mock private RecipeDao recipeDao;
    @Mock private TagDao tagDao;
    @Mock private QuestionDao questionDao;

    private AiService aiService;
    private AppUser user;


    @BeforeEach
    void setup() {
        user = new AppUser();
        user.setId(1);
        user.setEmail("a@a");
        user.setPseudo("User A");

        aiService = new AiService(client, recipeDao, tagDao, questionDao) {
            @Override
            protected String callGemini(String prompt) {
                return "Je vous recommande la paella";
            }
        };
    }


    @Test
    void getHistory_shouldReturnUserQuestions() {
        Question q = new Question();
        q.setId(1L);
        q.setContent("Je veux un dessert");
        q.setUser(user);
        q.setIncludeTags(List.of());
        q.setExcludeTags(List.of());

        when(questionDao.findByUserId(1)).thenReturn(List.of(q));

        List<Question> history = aiService.getHistory(user);

        assertEquals(1, history.size());
        assertEquals("Je veux un dessert", history.get(0).getContent());
    }
}
