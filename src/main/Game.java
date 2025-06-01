package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Player player;
    private WorldMap worldmap;
    private CommandRegistry commandRegistry;
    private boolean isGameOver;
    private Scanner inputScanner;

    public Game() {
        System.out.println("Initializing game (ULTRA-SIMPLE BETA)...");
        this.inputScanner = new Scanner(System.in);
        this.commandRegistry = new CommandRegistry();
        this.isGameOver = false;

        setupCommands();
        setupWorld();

        System.out.println("'help' to see the commands.");
        if (this.player != null && this.player.getCurrentLocation() != null) {
            System.out.println(this.player.getCurrentLocation().getFullDescription());
        } else {
            System.err.println("Err : No starting zone for the player... rip");
            this.isGameOver = true;
        }
    }

    private void setupCommands() {
        commandRegistry.addCommand(new MoveCommand());
        commandRegistry.addCommand(new LookCommand());
        commandRegistry.addCommand(new MapCommand());
        commandRegistry.addCommand(new HelpCommand());
        commandRegistry.addCommand(new TakeCommand());
        commandRegistry.addCommand(new InventoryCommand());
        commandRegistry.addCommand(new InspectCommand());
        commandRegistry.addCommand(new SayCommand());
        commandRegistry.addCommand(new UseCommand());
    }

    private void setupWorld() {
        this.worldmap = new WorldMap(2, 2);

        Location zone1 = new Location("Zone 1", "Starting zone");
        Location zone2 = new Location("Zone 2", "second zone");
        Location zone3 = new Location("Zone 3", "third zone");
        Location zone4 = new Location("Zone 4", "fourth zone");

        worldmap.addLocationToMap(zone1, 0, 0);
        worldmap.addLocationToMap(zone2, 0, 1);
        worldmap.addLocationToMap(zone3, 1, 0);
        worldmap.addLocationToMap(zone4, 1, 1);

        zone2.setLocked(true);
        zone3.setLocked(true);

        Key cleZone2 = new Key("key", "key to unlock a zone", zone2.getName());
        Key cleZone3 = new Key("key 2", "key to unlock a zone", zone3.getName());

        Letter l = new Letter("Weird Letter", "a weird letter", "1+1= ?");
        Letter l2 = new Letter("Weird Letter", "a weird letter", "a,b,c, ?");
        Enigma enigmeCalcul = new Enigma("A strange object", "IS THERE AN ENIGMA HERE ?!");
        zone1.addItem(enigmeCalcul);
        zone1.addItem(l);

        Enigma enigmeAlphabet = new Enigma("A strange object", "IS THERE AN ENIGMA HERE ?!");
        zone2.addItem(enigmeAlphabet);
        zone2.addItem(l2);
        zone1.setEnigma("1+1= ?", "2", cleZone2, enigmeCalcul.getName());
        zone2.setEnigma("a,b,c, ?", "d", cleZone3, enigmeAlphabet.getName());

        zone1.addExit("east", zone2.getName());
        zone2.addExit("west", zone1.getName());
        zone2.addExit("east", zone3.getName());
        zone3.addExit("west", zone2.getName());
        zone3.addExit("east", zone4.getName());
        zone4.addExit("west", zone3.getName());


        this.player = new Player(zone1);
        this.worldmap.updatePlayerGridPosition(zone1);
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
                System.out.println("Command '" + commandVerb + "' unknown. Write help instead of crying");
            }
        }
        inputScanner.close();
        System.out.println("C'est finito pépito mais bon y a pas de commande pour stop donc on le verra jamais rip");
    }
    public Player getPlayer() { return player; }
    public WorldMap getWorldMap() { return worldmap; }
    public CommandRegistry getCommandRegistry() { return commandRegistry; }
    public void setGameOver() { this.isGameOver = true; }
}