package project.questions;

import project.UserData;
import project.Validator;

public class CategoriesQuestion extends AbstractQuestion{
    public CategoriesQuestion() {
        super("Выбирите категорию событий:\n" +
                "   Кинопоказы\n" +
                "   Концерты\n" +
                "   Развлечения\n" +
                "   Выставки\n" +
                "   Фестивали\n" +
                "   Детям\n" +
                "   Вечеринки\n" +
                "   Квесты\n" +
                "   Спектакли\n" +
                "   Экскурсии");
    }
    @Override
    public boolean checkAnswer(String msg, UserData userData) {
        if (userData.getCurrentCategories() == null) {
            if (Validator.checkCategories(userData, msg)) {
                userData.setCurrentCategories(msg);
                return true;
            }
        }
        return false;
    }
}
