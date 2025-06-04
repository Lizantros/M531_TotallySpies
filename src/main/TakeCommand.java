package main;

public class TakeCommand implements ICommand {
    @Override
    public String getVerb() {
        return "take";
    }

    @Override
    public String getDescription() {
        return "Take an object";
    }

    @Override
    public void execute(Game game, String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Take what ?");
            return;
        }
        String itemName = String.join(" ", args);
        Player player = game.getPlayer();
        Location currentLocation = player.getCurrentLocation();

        Item itemToTake = null;

        for (Item item : currentLocation.getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToTake = item;
                break;
            }
        }

        if (itemToTake != null) {
            if (itemToTake.isTakeable()) {
                if (currentLocation.removeItemByName(itemToTake.getName()) != null) {
                    player.getInventory().addItem(itemToTake);
                    System.out.println(itemToTake.getName() + " was stolen by you... and added to your inventory");
                } else {
                    System.out.println("Impossible to take " + itemName + ".");
                }
            } else {
                System.out.println("You can't take " + itemName + "think... think!!!");
            }
        } else {
            System.out.println("This object : '" + itemName + "' doens't exist here...");
        }
    }
}