package main;

public class InspectCommand implements ICommand {
    @Override
    public String getVerb() {
        return "inspect";
    }

    @Override
    public String getDescription() {
        return "Inspecte un objet ou un lieu pour obtenir plus d'informations.";
    }

    @Override
    public void execute(Game game, String[] args) {
        /*if (args.length < 2) {
            System.out.println("Utilisation : inspect <objet>");
            return;
        }
        String itemName = args[1];
        for (Item item : game.getPlayer().getInventory().getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                System.out.println(item.getDescription());
                return;
            }
        }
        System.out.println("Objet non trouvé.");*/
    }
}
