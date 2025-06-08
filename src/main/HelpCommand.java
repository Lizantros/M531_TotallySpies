package main;

import java.util.List;

public class HelpCommand implements ICommand {
    @Override
    public String getVerb() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Show a list of all the commands";
    }

    @Override
    public void execute(Game game, String[] args) {
        System.out.println("\nCommands available :");
        List<ICommand> allCommands = game.getCommandRegistry().getAllCommands();
        Player player = game.getPlayer();
        boolean hasTeleportCrystal = player.getInventory().hasItem("Teleport Crystal");
        for (ICommand cmd : allCommands) {
            if (cmd.getVerb().equals("teleport") && !hasTeleportCrystal) {
                continue;
            }
            System.out.println(cmd.getVerb() + " - " + cmd.getDescription());
        }
        System.out.println();
    }
}