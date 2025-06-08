package main;

public class InventoryCommand implements ICommand {
    @Override
    public String getVerb() {
        return "inventory";
    }

    @Override
    public String getDescription() {
        return "Show the content of the inventory";
    }

    @Override
    public void execute(Game game, String[] args) {
        Player player = game.getPlayer();
        if (player != null && player.getInventory() != null) {
            System.out.println(player.getInventory().getItemsDisplay());
        } else {
            System.out.println("Can't access your inventory... It means the game is bugged... Shouldn't happen so either uninstall the game or pray very hard");
        }
    }
}