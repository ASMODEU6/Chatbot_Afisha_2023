package project.commands;

import project.UserData;

public class AuthorsCommand extends AbstractCommand {

    public void doCommand(UserData userData) {}

    public String getDescription() {
        return "информация об авторах";
    }
    public String getMessage(){
        return "Авторами проекта являются студенты 2-го курса специалитета \"Компьютерная безопасность\" Ботюк Илья и Кобелев Иван";
    }
}
