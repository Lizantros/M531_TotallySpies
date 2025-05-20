package main;

public class MoveCommand implements ICommand {
    @Override
    public String getVerb() {
        return "move";
    }

    @Override
    public String getDescription() {
        return "Déplace le joueur dans une direction (nord, sud, est, ouest).";
    }

    @Override
    public void execute(Game game, String[] args) {
        /*if (args.length < 2) {
            System.out.println("Utilisation : move <direction>");
            return;
        }
        String direction = args[1].toLowerCase();
        game.getWorldMap().movePlayer(direction);  méthode à implémenter dans Map
    }*/
    }
}