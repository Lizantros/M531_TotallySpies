package main;

public class TeleportCommand implements ICommand {
    @Override
    public String getVerb() {
        return "teleport";
    }

    @Override
    public String getDescription() {
        return "Teleport to a previously visited location by using the zones names";
    }

    @Override
    public void execute(Game game, String[] args) {
        Player player = game.getPlayer();


        if (!player.getInventory().hasItem("teleport crystal")) {
            System.out.println("You need a teleport crystal to use this command.");
            return;
        }

        if (args == null || args.length == 0) {
            System.out.println("Teleport where?");
            return;
        }

        String locationName = String.join(" ", args);
        WorldMap worldMap = game.getWorldMap();


        Location targetLocation = worldMap.getLocationByName(locationName);
        if (targetLocation == null) {
            System.out.println("What is this place " + locationName);
            return;
        }


        if (!player.hasVisited(targetLocation)) {
            System.out.println("You can only teleport to zones you have already visited");
            return;
        }


        player.setCurrentLocation(targetLocation);
        worldMap.updatePlayerGridPosition(targetLocation);
        System.out.println("The teleport crystal glows just a lil bit and transports you to " + targetLocation.getName() + ". Prepare yourself for motion sickness.");
        System.out.println(targetLocation.getFullDescription());
    }
}