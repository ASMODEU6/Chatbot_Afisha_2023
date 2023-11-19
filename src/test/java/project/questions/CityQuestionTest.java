package project.questions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityQuestionTest {

    public CityQuestion cityQuestion;

    @Test
    void checkAnswer() {
        cityQuestion = new CityQuestion();
        assertTrue(cityQuestion.checkAnswer("Екатеринбург"));
        assertFalse(cityQuestion.checkAnswer("Ekb"));
        assertFalse(cityQuestion.checkAnswer("Пермь"));
    }
}