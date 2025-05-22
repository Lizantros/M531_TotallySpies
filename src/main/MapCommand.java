package main;

import utils.Array2Dprinter;
import utils.IPrintable;

public class MapCommand implements ICommand {
    @Override
    public String getVerb() {
        return "map";
    }

    @Override
    public String getDescription() {
        return "Afficher la carte comme dans Dora l'exploratrice";
    }

    @Override
    public void execute(Game game, String[] args) {
        WorldMap worldMap = game.getWorldMap();
        IPrintable[][] gridToPrint = worldMap.getGridForPrinting();
        int playerR = worldMap.getPlayerGridR();
        int playerC = worldMap.getPlayerGridC();

        String mapString = Array2Dprinter.print2DArray(gridToPrint, playerR, playerC);
        System.out.println("\n--- C'est la carte, c'est la carte ! ---");
        System.out.println(mapString);
        System.out.println("Cette carte vous est founrie par le terrifiant farfadet des forêt");





    }
}