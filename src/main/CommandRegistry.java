package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandRegistry {
    private Map<String, ICommand> commands;

    public CommandRegistry() {
        this.commands = new HashMap<>();
    }

    public void addCommand(ICommand command) {
        commands.put(command.getVerb().toLowerCase(), command);
    }

    public ICommand getCommand(String verb) {
        return commands.get(verb.toLowerCase());
    }

    public List<ICommand> getAllCommands() {
        return new ArrayList<>(commands.values());
    }
}