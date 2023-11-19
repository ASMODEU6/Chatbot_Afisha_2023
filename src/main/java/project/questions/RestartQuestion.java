package project.questions;

public class RestartQuestion extends AbstractQuestion {
    public RestartQuestion() {
        super("\nВведите номер страницы или напишите мне \"старт\" для нового поиска.");
    }

    public boolean checkAnswer(String msg /*, UserData userData*/){
        if (msg.equalsIgnoreCase("СТАРТ")){
            return true;
        } else {
            int i = Integer.parseInt(msg);
        }
        return false;
    }
}
