package main;

public class SayCommand implements ICommand {
    @Override
    public String getVerb() {
        return "say";
    }

    @Override
    public String getDescription() {
        return "Say something to resolve enigma";
    }

    @Override
    public void execute(Game game, String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Say what?");
            return;
        }
        String saidText = String.join(" ", args);
        Player player = game.getPlayer();
        Location currentLocation = player.getCurrentLocation();

        System.out.println("YOU SAY : \"" + saidText + "\"");

        if (currentLocation.hasEnigma() && !currentLocation.isEnigmaSolved()) {
            currentLocation.solveEnigma(saidText);
        } else if (currentLocation.isEnigmaSolved()) {
            System.out.println("Enigma already resolved");
        }
    }
}