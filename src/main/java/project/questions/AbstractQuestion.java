package project.questions;

import project.UserData;

public class AbstractQuestion {
    private final String question;
    public AbstractQuestion(String question){
        this.question = question;
    }
    public String getQuestion(){
        return question;
    }

    public boolean checkAnswer(String msg, UserData userData){
        return false;
    }


}
