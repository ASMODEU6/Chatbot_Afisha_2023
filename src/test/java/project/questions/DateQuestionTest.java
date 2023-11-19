package project.questions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateQuestionTest {

    public DateQuestion dateQuestion;

    @Test
    void checkAnswer() {
        dateQuestion = new DateQuestion();
        assertTrue(dateQuestion.checkAnswer("01-12-2023"));
        assertFalse(dateQuestion.checkAnswer("01.12.2023"));
        assertFalse(dateQuestion.checkAnswer("01/12/2023"));
        assertFalse(dateQuestion.checkAnswer("01122023"));
    }
}