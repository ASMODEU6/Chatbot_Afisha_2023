package project.commands;

import project.UserData;

public abstract class AbstractCommand {

    public abstract void doCommand(UserData userData);

    public String getDescription(){
        return null;
    }

    public String getMessage(){
        return null;
    }
}
