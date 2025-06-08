package main;

public class SayCommand implements ICommand {
    @Override
    public String getVerb() {
        return "say";
    }

    @Override
    public String getDescription() {
        return "Say an enigma's good answer in the good zone to resolve an enigma";
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
            System.out.println("Enigma is already resolved... Come on, you were kinda the person who solved it...");
        }
    }
}