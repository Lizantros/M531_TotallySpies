package main;

public class MoveCommand implements ICommand {
    @Override
    public String getVerb() {
        return "move";
    }

    @Override
    public String getDescription() {
        return "Move to another zone";
    }

    @Override
    public void execute(Game game, String[] args) {
        if (args == null || args.length < 1) {
            System.out.println("You need to give a direction... north/south/east/west... Come on");
            return;
        }
        String direction = args[0].toLowerCase();
        Player player = game.getPlayer();
        WorldMap worldMap = game.getWorldMap();
        Location currentLocation = player.getCurrentLocation();

        String nextLocationName = currentLocation.getExit(direction);
        if (nextLocationName == null) {
            System.out.println("Can't go there uh");
            return;
        }

        Location nextLocation = worldMap.getLocationByName(nextLocationName);
        if (nextLocation == null) {
            System.out.println("The destination '" + nextLocationName + "' doens't exist... bruh");
            return;
        }

        if (nextLocation.isLocked()) {
            System.out.println("The zone : " + nextLocation.getName() + " is locked... sorrow and sadness");
        } else {
            player.setCurrentLocation(nextLocation);
            worldMap.updatePlayerGridPosition(nextLocation);
            System.out.println("You're moving to " + direction);
            System.out.println(nextLocation.getFullDescription());
        }
    }
}