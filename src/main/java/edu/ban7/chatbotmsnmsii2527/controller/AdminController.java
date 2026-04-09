package edu.ban7.chatbotmsnmsii2527.controller;

import edu.ban7.chatbotmsnmsii2527.dao.RecipeDao;
import edu.ban7.chatbotmsnmsii2527.dto.RecipeStatsDTO;
import edu.ban7.chatbotmsnmsii2527.model.Recipe;
import edu.ban7.chatbotmsnmsii2527.security.IsAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@IsAdmin
public class AdminController {

    protected final RecipeDao recipeDao;

    @GetMapping("/recipes/stats")
    public ResponseEntity<List<RecipeStatsDTO>> recipeStats() {

        List <RecipeStatsDTO> stats = recipeDao.findAllByOrderByReturnCountDesc()
                .stream()
                .map(r->new RecipeStatsDTO(r.getId(), r.getName(),r.getReturnCount()))
                .toList();
        return ResponseEntity.ok(stats);
    }
}
