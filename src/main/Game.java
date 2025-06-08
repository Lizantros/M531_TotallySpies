package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Player player;
    private WorldMap worldmap;
    private CommandRegistry commandRegistry;
    private boolean isGameOver;
    private Scanner inputScanner;
    private List<String> commandHistory;
    private static final String SAVE_FILENAME = "savefile.txt";

    public Game() {
        this.commandHistory = new ArrayList<>();
        this.inputScanner = new Scanner(System.in);

        if (Files.exists(Paths.get(SAVE_FILENAME))) {
            System.out.println("A save was found. Do you want to play it");
            String response = inputScanner.nextLine().trim().toLowerCase();
            if (response.equals("yes") || response.equals("y")) {
                loadGame();
                return;
            }
        }
        startNewGame();
    }
    private void startNewGame() {
        System.out.println("Initializing the game (ULTRA-SIMPLE BETA)!!!!!!!!!!!!!!!!!!!!");
        this.commandRegistry = new CommandRegistry();
        this.isGameOver = false;

        setupCommands();
        setupWorld();

        System.out.println("'help' to see the commands.");
        if (this.player != null && this.player.getCurrentLocation() != null) {
            System.out.println(this.player.getCurrentLocation().getFullDescription());
        } else {
            System.err.println("No starting zone");
            this.isGameOver = true;
        }
    }

    private void loadGame() {
        System.out.println("Loading saved game...");

        this.commandRegistry = new CommandRegistry();
        this.isGameOver = false;
        setupCommands();
        setupWorld();

        this.commandHistory.clear();

        try {
            List<String> lines = Files.readAllLines(Paths.get(SAVE_FILENAME));

            for (String cl : lines) {
                if (!cl.trim().isEmpty()) {
                    executeCommandFromString(cl, false);
                }
            }

            System.out.println("Game loaded successfully!");
            System.out.println("'help' to see the commands.");
            if (this.player != null && this.player.getCurrentLocation() != null) {
                System.out.println(this.player.getCurrentLocation().getFullDescription());
            }

        } catch (IOException e) {
            System.out.println("Error loading saved game: " + e.getMessage());
            System.out.println("Starting new game instead...");
            startNewGame();
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
        commandRegistry.addCommand(new SaveCommand());
        commandRegistry.addCommand(new TeleportCommand());
    }

    private void setupWorld() {
        this.worldmap = new WorldMap(3, 4);

        Location zone1 = new Location("Dawn of Mysteries", "A peaceful clearing bathed in soft morning light. An old sign marks the beginning of an adventure.\nThe air is fresh, and a light breeze rustles the leaves of the surrounding trees. This is where your quest begins.");
        Location zone2 = new Location("The Ancients' Whisper", "A dense thicket where a centuries-old oak tree stands. At its feet, a small stone stele seems to have been engraved long ago.\nThe wind whistles softly through the branches, like whispered secrets.");
        Location zone3 = new Location("The Clear Spring", "A spring of crystal-clear water gushes from a mossy rock, forming a small pool\nThe place is calm, ideal for reflection. Colorful butterflies flutter above the water.");
        Location zone4 = new Location("The Forbidden Ruins", "The remains of an ancient watchtower, overgrown with vegetation.\nThe entrance is blocked by a heavy, rusted gate, secured by a massive padlock. A sense of mystery and danger emanates from this place.");
        Location zone5 = new Location("Peaceful Clearing", "A oeaceful clearing... Or... Is it really peaceful ?!");
        Location zone6 = new Location("The Forgotten Camp", "A weird camp... There are blood stains here and there");
        Location zone7 = new Location("The Shaky Bridge", "An old rope and plank bridge spans a misty ravine. Several planks are missing, and the structure looks unstable.\nA thick chain blocks access, locked by a complex padlock.");
        Location zone8 = new Location("The Echoing Cave", "The gaping entrance of a dark cave. Water drips from the ceiling with a regular echo.\nFaintly luminous crystals line some of the walls, creating a mysterious atmosphere.");
        Location zone9 = new Location("The Whispering Garden", "A once magnificent garden, now overgrown with tall grasses and wildflowers.\nStone statues, half-hidden by vegetation, seem to watch the player. The wind makes strange sounds here.");
        Location zone10 = new Location("The Sealed Crypt", "The entrance to a stone crypt, closed by a heavy stone door engraved with ancient symbols.\nA magical seal seems to keep it shut, in addition to a complex lock.");
        Location zone11 = new Location("The Celestial Observatory", "An elevated rocky platform, offering a breathtaking view of the sky.\nAn ancient, though rusty, telescope is pointed towards the stars. The place inspires contemplation.");
        Location zone12 = new Location("The World's Peak", "The highest point in the region, a windswept rocky peak. \nThe panoramic view is spectacular, overlooking all other zones. It is a place of triumph and reflection.");

        worldmap.addLocationToMap(zone1, 0, 0);
        worldmap.addLocationToMap(zone2, 0, 1);
        worldmap.addLocationToMap(zone3, 0, 2);
        worldmap.addLocationToMap(zone4, 0, 3);
        worldmap.addLocationToMap(zone5, 1, 0);
        worldmap.addLocationToMap(zone6, 1, 1);
        worldmap.addLocationToMap(zone7, 1, 2);
        worldmap.addLocationToMap(zone8, 1, 3);
        worldmap.addLocationToMap(zone9, 2, 0);
        worldmap.addLocationToMap(zone10, 2, 1);
        worldmap.addLocationToMap(zone11, 2, 2);
        worldmap.addLocationToMap(zone12, 2, 3);

        zone4.setLocked(true);
        zone7.setLocked(true);
        zone10.setLocked(true);

        Key k1 = new Key("Key to the Bridge", "A slender, bronze key, surprisingly lightweight. It bears etchings of swirling winds and seems to be designed for a lock exposed to the elements, like one found on a precarious crossing.", zone7.getName());
        Key k2 = new Key("Key to the Ruins", "An old, heavy iron key, its surface covered in a fine layer of rust and moss. It feels ancient and hums with a faint, earthy energy, perfectly suited for a forgotten place.", zone4.getName());
        Key k3 = new Key("Key to the Crypt", "A dark, ornate key made of a strange, cold metal. Intricate geometric patterns are carved into its bow, hinting at the complex mechanisms of a deeply sealed chamber.", zone10.getName());

        Letter l1 = new Letter("Sylvan Guardian's Scroll", "A weathered parchment bearing an odd question, seemingly left by a protector of the woods.", "What does a cat do when it's happy? Say the answer ALOUD in the zone to the EAST.");
        Letter l2 = new Letter("Lost Traveler's Note", "A hastily scribbled note on a piece of rough paper, asking a seemingly simple historical question.", "What color is Henry IV's white horse? Say the answer ALOUD in the zone to the NORTH-EAST.");
        Letter l3 = new Letter("Ancient Architect's Cipher", "A brittle piece of papyrus with a geometric query, penned by someone with an eye for design.", "How many sides does a triangle have? Say the answer ALOUD in the zone to the SOUTH-WEST.");

        Enigma enigmeRuins = new Enigma("A strange object", "IS THERE AN ENIGMA HERE ?!");
        Enigma enigmeBridge = new Enigma("A strange object", "IS THERE AN ENIGMA HERE ?!");
        Enigma enigmeCrypt = new Enigma("A strange object", "IS THERE AN ENIGMA HERE ?!");

        TeleportCrystal crystal = new TeleportCrystal("Teleport Crystal", "Yay ! You can teleport to places you've already visited now !!.");

        zone3.setEnigma("What does a cat do when it's happy?", "it purrs", k2, enigmeRuins.getName());
        zone5.setEnigma("What color is Henry IV's white horse?", "White", k1, enigmeBridge.getName());
        zone9.setEnigma("How many sides does a triangle have?", "3", k3, enigmeCrypt.getName());

        zone1.addExit("east", zone2.getName());
        zone1.addExit("south", zone5.getName());

        zone2.addExit("west", zone1.getName());
        zone2.addExit("east", zone3.getName());
        zone2.addExit("south", zone6.getName());

        zone3.addExit("west", zone2.getName());
        zone3.addExit("east", zone4.getName());
        zone3.addExit("south", zone7.getName());

        zone4.addExit("south", zone8.getName());
        zone4.addExit("west", zone3.getName());

        zone5.addExit("north", zone1.getName());
        zone5.addExit("east", zone6.getName());
        zone5.addExit("south", zone9.getName());

        zone6.addExit("north", zone2.getName());
        zone6.addExit("east", zone7.getName());
        zone6.addExit("west", zone5.getName());
        zone6.addExit("south", zone10.getName());

        zone7.addExit("north", zone3.getName());
        zone7.addExit("east", zone8.getName());
        zone7.addExit("west", zone6.getName());
        zone7.addExit("south", zone11.getName());

        zone8.addExit("north", zone4.getName());
        zone8.addExit("west", zone7.getName());
        zone8.addExit("south", zone12.getName());

        zone9.addExit("north", zone5.getName());
        zone9.addExit("east", zone10.getName());

        zone10.addExit("north", zone6.getName());
        zone10.addExit("west", zone9.getName());
        zone10.addExit("east", zone11.getName());

        zone11.addExit("north", zone7.getName());
        zone11.addExit("west", zone10.getName());
        zone11.addExit("east", zone11.getName());

        zone12.addExit("north", zone8.getName());
        zone12.addExit("west", zone11.getName());

        zone2.addItem(l1);
        zone5.addItem(l2);
        zone8.addItem(l3);

        zone3.addItem(enigmeRuins);
        zone6.addItem(enigmeBridge);
        zone9.addItem(enigmeCrypt);
        zone12.addItem(crystal);

        this.player = new Player(zone1);
        this.worldmap.updatePlayerGridPosition(zone1);
    }
    private void executeCommandFromString(String commandLine, boolean addToHistory) {
        if (commandLine.trim().isEmpty()) {
            return;
        }

        String commandVerb;
        String apresLeVerb = "";
        String[] commandArgs;
        int firstSpaceIndex = commandLine.indexOf(' ');
        if (firstSpaceIndex == -1) {
            commandVerb = commandLine.toLowerCase();
            commandArgs = new String[0];
        } else {
            commandVerb = commandLine.substring(0, firstSpaceIndex).toLowerCase();
            if (firstSpaceIndex < commandLine.length() - 1) {
                apresLeVerb = commandLine.substring(firstSpaceIndex + 1).trim();
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

        if (addToHistory && !commandVerb.equals("save")) {
            commandHistory.add(commandLine);
        }

        ICommand commandToExecute = commandRegistry.getCommand(commandVerb);
        if (commandToExecute != null) {
            commandToExecute.execute(this, commandArgs);
        } else {
            System.out.println("Command " + commandVerb + " is unknown. Write help to get the command's list");
        }
    }

    public void run() {
        String line;
        while (!isGameOver) {
            System.out.print("\n> ");
            line = inputScanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            executeCommandFromString(line, true);
        }
        inputScanner.close();
        System.out.println("See ya");
    }
    public Player getPlayer() { return player; }
    public WorldMap getWorldMap() { return worldmap; }
    public CommandRegistry getCommandRegistry() { return commandRegistry; }
    public void setGameOver() { this.isGameOver = true; }
    public List<String> getCommandHistory() { return new ArrayList<>(commandHistory); }
}