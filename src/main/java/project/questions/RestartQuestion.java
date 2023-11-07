package project.questions;

import project.UserData;
public class RestartQuestion extends AbstractQuestion {
    public RestartQuestion() {
        super("\nВведите номер страницы или напишите мне \"старт\" для нового поиска.");
    }

    @Override
    public boolean checkAnswer(String msg, UserData userData){
        if (msg.equalsIgnoreCase("СТАРТ")){
            userData.setCurrentQuestion(0);
            userData.setCurrentCity(null);
            userData.setCurrentDate(null);
            userData.setCurrentCategories(null);
            userData.setCurrentPage(0);
        } else if (Integer.parseInt(msg) > 0) {

        }
        return false;
    }
}
