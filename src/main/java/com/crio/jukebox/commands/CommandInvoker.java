package com.crio.jukebox.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.exceptions.CommandNotFoundException;

public class CommandInvoker {

    private static CommandInvoker commandInvoker;
    private final static Map<String, ICommand> commandMap = new HashMap<>();

    private CommandInvoker() {
    }

    public void registerCommand(String commandName, ICommand command) {
        commandMap.put(commandName, command);
    }

    private ICommand getCommand(String commandName) {
        return commandMap.get(commandName);
    }

    public void executeCommand(String commandName, List<String> tokens) throws CommandNotFoundException {

        ICommand command = getCommand(commandName);
        if(command == null) throw new CommandNotFoundException("Given command doesn't exist!");
        command.execute(tokens);
    } 

    public static CommandInvoker getCommandInvoker() {
        if(commandInvoker == null) {
            commandInvoker = new CommandInvoker();
        }
        return commandInvoker;
    }
    
}
