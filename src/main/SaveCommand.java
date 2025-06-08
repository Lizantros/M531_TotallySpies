package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveCommand implements ICommand {
    private static final String SAVE_FILENAME = "savefile.txt";

    @Override
    public String getVerb() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "Save your game";
    }

    @Override
    public void execute(Game game, String[] args) {
        List<String> commandHistory = game.getCommandHistory();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILENAME))) {
            for (String command : commandHistory) {
                writer.write(command);
                writer.newLine();
            }
            System.out.println("The game was saved... Maybe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}