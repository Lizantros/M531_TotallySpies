package main;

import main.Game;
import main.ICommand;
import main.Item;
import main.Location;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand implements ICommand {

    private Game game;

    @Override
    public String getDescription() {
        return "Sauvegarde de la partie.";
    }

    @Override
    public String getVerb() {
        return "save";
    }

    public SaveCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute(Game game, String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"))) {
            // Sauvegarde de la position
            writer.write("position=" + game.getPlayer().getCurrentLocation().getName());
            writer.newLine();

            // Sauvegarde de l’inventaire
            for (Item item : game.getPlayer().getInventory().getItems()) {
                writer.write("item=" + item.getName());
                writer.newLine();
            }

            // Sauvegarde des lieux visités
            for (Location loc : game.getPlayer().getVisitedLocations()) {
                writer.write("visited=" + loc.getName());
                writer.newLine();
            }

            System.out.println("Jeu sauvegardé avec succès !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde.");
            e.printStackTrace();
        }
    }

}
