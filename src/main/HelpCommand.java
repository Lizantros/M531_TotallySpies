package main;

public class HelpCommand implements ICommand {
    @Override
    public String getVerb() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Affiche la liste des commandes disponibles.";
    }

    @Override
    public void execute(Game game, String[] args) {
        /*for (ICommand command : game.getCommandManager().getAllCommands()) {
            System.out.println(command.getVerb() + " : " + command.getDescription());
        }*/
    }
}
