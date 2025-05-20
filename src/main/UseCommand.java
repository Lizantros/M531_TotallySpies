package main;

public class UseCommand implements ICommand{
    @Override
    public String getVerb() {
        return "use";
    }

    @Override
    public String getDescription() {
        return "Utilise un objet de l'inventaire.";
    }

    @Override
    public void execute(Game game, String[] args) {
       /* if (args.length < 2) {
            System.out.println("Utilisation : use <nom_objet>");
            return;
        }
        String itemName = args[1];
        Item itemToUse = null;
        for (Item item : game.getPlayer().getInventory().getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToUse = item;
                break;
            }
        }
        if (itemToUse == null) {
            System.out.println("Objet non trouvé dans l'inventaire.");
            return;
        }
        itemToUse.useOnLocation(game.getWorldMap().getPlayerLocation()); */
    }
}
