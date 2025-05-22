package main;

public class LookCommand implements ICommand {
    @Override
    public String getVerb() {
        return "look";
    }

    @Override
    public String getDescription() {
        return "Tu sors une loupe et tu regardes autour de toi... Comme l'inspecteur Gadget ? (ancienne la ref)";
    }

    @Override
    public void execute(Game game, String[] args) {
        Location currentLocation = game.getPlayer().getCurrentLocation();
        System.out.println(currentLocation.getFullDescription());
    }
}