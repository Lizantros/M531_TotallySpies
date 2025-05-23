package main;

import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

public class Game {
    private Player player;
    private WorldMap worldmap;
    private CommandRegistry commandRegistry;
    private boolean isGameOver;
    private Scanner inputScanner;

    public Game(){
        System.out.println("Initializing game...");
        this.inputScanner = new Scanner(System.in);
        this.commandRegistry = new CommandRegistry();
        this.isGameOver = false;

        setupCommands();
        setupWorld();

        System.out.println("'help' pour voir les très nombreuses commandes");
        System.out.println(this.player.getCurrentLocation().getFullDescription());
    }

    private void setupCommands() {
        commandRegistry.addCommand(new MoveCommand());
        commandRegistry.addCommand(new LookCommand());
        commandRegistry.addCommand(new MapCommand());
        commandRegistry.addCommand(new HelpCommand());
    }

    private void setupWorld() {
        this.worldmap = new WorldMap(2, 3);


        Location foret = new Location("La maison de Eddie Malou", "Je sais pas quoi dire");
        Location clairiere = new Location("Clairière", "Quelques rayons de soleil percent le feuillage.");
        Location riviere = new Location("Rivière", "Une rivière clapote doucement.");
        Location pont = new Location("Pont de Pierre", "Un vieux pont de pierre enjambe la rivière.");
        Location montagne = new Location("Montagne", "Un sentier escarpé monte vers les sommets.");
        Location pierre = new Location("La maison de Pierre Duriff", "Le grand monarch joue amicalement de la flûte");


        worldmap.addLocationToMap(foret, 0, 0);
        worldmap.addLocationToMap(clairiere, 0, 1);
        worldmap.addLocationToMap(riviere, 0, 2);
        worldmap.addLocationToMap(montagne, 1, 0);
        worldmap.addLocationToMap(pont, 1, 2);
        worldmap.addLocationToMap(pierre, 1, 1);


        foret.addExit("east", clairiere.getName());
        foret.addExit("south", montagne.getName());
        clairiere.addExit("west", foret.getName());
        clairiere.addExit("east", riviere.getName());
        clairiere.addExit("south", pierre.getName());
        riviere.addExit("west", clairiere.getName());
        riviere.addExit("south", pont.getName());
        pont.addExit("north", riviere.getName());
        pont.setLocked(true);
        montagne.addExit("north", foret.getName());
        pierre.addExit("east", pont.getName());
        pierre.addExit("west", montagne.getName());
        pierre.addExit("north", clairiere.getName());


        this.player = new Player(foret);
        this.worldmap.updatePlayerGridPosition(foret);
    }

    public void run() {
        System.out.println("Oh shit, here we go again");

        String line;
        while (!isGameOver) {
            System.out.print("\n> ");
            line = inputScanner.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String commandVerb;
            String apresLeVerb = "";
            String[] commandArgs;


            int firstSpaceIndex = line.indexOf(' ');

            if (firstSpaceIndex == -1) {
                commandVerb = line.toLowerCase();
                commandArgs = new String[0];
            } else {
                commandVerb = line.substring(0, firstSpaceIndex).toLowerCase();
                if (firstSpaceIndex < line.length() - 1) {
                    apresLeVerb = line.substring(firstSpaceIndex + 1).trim();
                }
                if (apresLeVerb.isEmpty()) {
                    commandArgs = new String[0];
                } else {
                    String[] potentialArgs = apresLeVerb.split(" ");
                    List<String> finalListArgs = new ArrayList<>();
                    for (String arg : potentialArgs) {
                        if (!arg.isEmpty()) {
                            finalListArgs.add(arg);
                        }
                    }
                    commandArgs = finalListArgs.toArray(new String[0]);
                }
            }

            ICommand commandToExecute = commandRegistry.getCommand(commandVerb);
            if (commandToExecute != null) {
                commandToExecute.execute(this, commandArgs);
            } else {
                System.out.println("Commande '" + commandVerb + "' inconnu. Ecris 'help' si tu as envie de pleurer");
            }
        }
        inputScanner.close();
        System.out.println("C'est finito pépito");
    }


    public Player getPlayer() { return player; }
    public WorldMap getWorldMap() { return worldmap; }
    public CommandRegistry getCommandRegistry() { return commandRegistry; }
    public void setGameOver() { this.isGameOver = true; }
}