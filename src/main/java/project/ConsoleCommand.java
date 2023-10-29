package project;

import project.commands.*;

import java.util.HashMap;

public class ConsoleCommand {
    private final HashMap<String, AbstractCommand> commandHashMap;
    public ConsoleCommand(){
        commandHashMap = new HashMap<>();
        commandHashMap.put("/about", new AboutCommand());
        commandHashMap.put("/start", new StartCommand());
        commandHashMap.put("/authors", new AuthorsCommand());
        commandHashMap.put("/help", new HelpCommand(commandHashMap));
    }
    public String findCommand(UserData userData, String text){
        if (commandHashMap.containsKey(text)) {
            commandHashMap.get(text).doCommand(userData);
            return commandHashMap.get(text).getMessage();
        }
        return text;
    }

}
