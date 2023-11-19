package project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    public Validator validator = new Validator();

    @Test
    void checkCity() {
        assertTrue(validator.checkCity("Москва"));
        assertFalse(validator.checkCity("Moskow"));
    }

    @Test
    void checkCategories() {
        assertTrue(validator.checkCategories("Концерты"));
        assertFalse(validator.checkCategories("Koncert"));
    }
}