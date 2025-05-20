package main;

public class MapCommand implements ICommand {
    @Override
    public String getVerb() {
        return "map";
    }

    @Override
    public String getDescription() {
        return "Affiche la carte du jeu.";
    }

    @Override
    public void execute(Game game, String[] args) {
        //game.getWorldMap().display(); Méthode supposée afficher la map
    }
}
