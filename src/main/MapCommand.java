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
        return "Show the map like in Dora l'exploratrice";
    }

    @Override
    public void execute(Game game, String[] args) {
        WorldMap worldMap = game.getWorldMap();
        IPrintable[][] gridToPrint = worldMap.getGridForPrinting();
        int playerR = worldMap.getPlayerGridR();
        int playerC = worldMap.getPlayerGridC();

        String mapString = Array2Dprinter.print2DArray(gridToPrint, playerR, playerC);
        System.out.println("\n--- it's the map, it's the map ! ---");
        System.out.println(mapString);
        System.out.println("This map is being provided by a scary Vampire");





    }
}
