package main;

public class TakeCommand implements ICommand {
    @Override
    public String getVerb() {
        return "take";
    }

    @Override
    public String getDescription() {
        return "Prend un objet et l'ajoute à l'inventaire.";
    }

    @Override
    public void execute(Game game, String[] args) {
        /*if (args.length < 2) {
            System.out.println("Utilisation : take <nom_objet>");
            return;
        }
        String itemName = args[1];
        ItemManager itemManager = game.getItemManager();
        Location currentLocation = game.getWorldMap().getPlayerLocation();
        for (Item item : currentLocation.getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemManager.takeItem(game.getPlayer(), currentLocation, item);
                System.out.println(itemName + " a été ajouté à votre inventaire.");
                return;
            }
        }
        System.out.println("Objet non trouvé ici.");
    }
}{*/
    }
}
