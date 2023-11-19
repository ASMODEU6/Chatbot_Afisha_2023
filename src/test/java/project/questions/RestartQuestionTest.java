package project.questions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestartQuestionTest {

    public RestartQuestion restartQuestion;

    @Test
    void checkAnswer() {
        restartQuestion = new RestartQuestion();
        assertTrue(restartQuestion.checkAnswer("старт"));
        assertTrue(restartQuestion.checkAnswer("СТАРТ"));
        assertFalse(restartQuestion.checkAnswer("нет"));
    }
}