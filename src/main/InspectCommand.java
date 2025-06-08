package main;

public class InspectCommand implements ICommand {
    @Override
    public String getVerb() {
        return "inspect";
    }

    @Override
    public String getDescription() {
        return "inspect an object";
    }

    @Override
    public void execute(Game game, String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Inspect what ? You need to put the name of the object you wish to inspect");
            return;
        }
        String itemName = String.join(" ", args);
        Player player = game.getPlayer();
        Item itemToInspect;

        itemToInspect = player.getInventory().getItemByName(itemName);

        if (itemToInspect == null) {
            for (Item itemInRoom : player.getCurrentLocation().getItems()) {
                if (itemInRoom.getName().equalsIgnoreCase(itemName)) {
                    itemToInspect = itemInRoom;
                    break;
                }
            }
        }

        if (itemToInspect != null) {
            if (itemToInspect instanceof Letter) {
                ((Letter) itemToInspect).inspect();
            } else {
                System.out.println("You inspect " + itemToInspect.getName() + ":");
                System.out.println(itemToInspect.getDescription());
            }
        } else {
            System.out.println("There isn't any object called '" + itemName + "' to inspect");
        }
    }
}