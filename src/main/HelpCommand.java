package main;

import java.util.List;

public class HelpCommand implements ICommand {
    @Override
    public String getVerb() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Affiche les commandes";
    }

    @Override
    public void execute(Game game, String[] args) {
        System.out.println("\nCommandes disponibles :");
        List<ICommand> allCommands = game.getCommandRegistry().getAllCommands();
        for (ICommand cmd : allCommands) {
            System.out.println(cmd.getVerb() + " - " + cmd.getDescription());
        }
        System.out.println();
    }
}