package main;

public class UseCommand implements ICommand {
    @Override
    public String getVerb() {
        return "use";
    }

    @Override
    public String getDescription() {
        return "Use an object in your inventory";
    }

    @Override
    public void execute(Game game, String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Using what objet ????????");
            return;
        }
        String itemName = String.join(" ", args);
        Player player = game.getPlayer();
        Item itemToUse = player.getInventory().getItemByName(itemName);

        if (itemToUse == null) {
            System.out.println("You don't have this object called '" + itemName + "' in your inventory... Don't even remember what you take uh...");
            return;
        }

        if (itemToUse instanceof Key) {
            ((Key) itemToUse).use(game);
        }
        else {
            System.out.println("You can't use " + itemToUse.getName());
        }
    }
}
