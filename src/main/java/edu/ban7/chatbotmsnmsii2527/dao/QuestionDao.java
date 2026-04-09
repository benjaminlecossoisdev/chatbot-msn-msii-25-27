package edu.ban7.chatbotmsnmsii2527.dao;

import edu.ban7.chatbotmsnmsii2527.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question,Long> {
    List<Question> findByUserId(Integer user_id);
}
