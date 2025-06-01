package main;

public class Key extends Item {
    private String targetLocationName; // Nom de la zone que cette clé déverrouille

    public Key(String name, String description, String targetLocationName) {
        super(name, description);
        this.targetLocationName = targetLocationName.toLowerCase();
    }

    public String getTargetLocationName() {
        return targetLocationName;
    }

    public boolean use(Game game) {
        Player player = game.getPlayer();
        Location currentLocation = player.getCurrentLocation();
        WorldMap worldMap = game.getWorldMap();

        for (String exitDirection : currentLocation.getExits().keySet()) {
            String adjacentLocationName = currentLocation.getExit(exitDirection);
            if (adjacentLocationName != null && adjacentLocationName.equalsIgnoreCase(this.targetLocationName)) {
                Location targetLocation = worldMap.getLocationByName(adjacentLocationName);
                if (targetLocation != null && targetLocation.isLocked()) {
                    targetLocation.setLocked(false);
                    System.out.println("You use " + getName() + ". The beautiful zone '" + targetLocation.getName() + "' is now unlocked !");
                    return true;
                } else if (targetLocation != null && !targetLocation.isLocked()) {
                    System.out.println("The zone '" + targetLocation.getName() + "' is already entirely and fully unlocked.");
                    return false;
                }
            }
        }
        System.out.println(getName() + " The zone you're in or a nearby one doesn't seem to be able to unlocked.");
        return false;
    }
}