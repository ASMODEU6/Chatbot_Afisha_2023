package project.questions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriesQuestionTest {

    public CategoriesQuestion categoriesQuestion;

    @Test
    void checkAnswer() {
        categoriesQuestion = new CategoriesQuestion();
        assertTrue(categoriesQuestion.checkAnswer("Кинопоказы"));
        assertFalse(categoriesQuestion.checkAnswer("Kinopokazy"));
    }
}