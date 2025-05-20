package main;

public class LookCommand implements ICommand {
    @Override
    public String getVerb() {
        return "look";
    }

    @Override
    public String getDescription() {
        return "Regarde autour de soi pour voir les objets présents.";
    }

    @Override
    public void execute(Game game, String[] args) {
        /*Location loc = game.getWorldMap().getPlayerLocation();
        System.out.println("Vous êtes à : " + loc.getName());
        for (Item item : loc.getItems()) {
            System.out.println("Objet présent : " + item.getName());
        }*/
    }
}
