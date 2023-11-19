package project.questions;
import project.Validator;

public class CategoriesQuestion extends AbstractQuestion{
    public CategoriesQuestion() {
        super("""
                Выбирите категорию событий:
                   Кинопоказы
                   Концерты
                   Развлечения
                   Выставки
                   Фестивали
                   Детям
                   Вечеринки
                   Квесты
                   Спектакли
                   Экскурсии""");
    }

    public boolean checkAnswer(String msg /*, UserData userData*/) {
        return Validator.checkCategories(/*userData,*/ msg);
    }
}
