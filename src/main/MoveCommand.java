package main;

public class MoveCommand implements ICommand {
    @Override
    public String getVerb() {
        return "move";
    }

    @Override
    public String getDescription() {
        return "Se déplacer vers une nouvelle zone";
    }

    @Override
    public void execute(Game game, String[] args) {
        if (args == null || args.length < 1) {
            System.out.println("Faut dire ou tu veux aller le s");
            return;
        }
        String direction = args[0].toLowerCase();
        Player player = game.getPlayer();
        WorldMap worldMap = game.getWorldMap();
        Location currentLocation = player.getCurrentLocation();

        String nextLocationName = currentLocation.getExit(direction);
        if (nextLocationName == null) {
            System.out.println("Tu peux pas aller là bas le s");
            return;
        }

        Location nextLocation = worldMap.getLocationByName(nextLocationName);
        if (nextLocation == null) {
            System.out.println("La destination '" + nextLocationName + "' n'existe pas... oui...");
            return;
        }

        if (nextLocation.isLocked()) {
            System.out.println("La zone : " + nextLocation.getName() + " est fermé... Dommage... Ou pas ! Cheh");
        } else {
            player.setCurrentLocation(nextLocation);
            worldMap.updatePlayerGridPosition(nextLocation);
            System.out.println("Tu te déplace vers " + direction);
            System.out.println(nextLocation.getFullDescription());
        }
    }
}