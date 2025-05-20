package main;

public class ListCommand implements ICommand {
    @Override
    public String getVerb() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Liste les objets dans l'inventaire du joueur.";
    }

    @Override
    public void execute(Game game, String[] args) {
        /*for (Item item : game.getPlayer().getInventory().getItems()) {
            System.out.println("- " + item.getName());
        }*/
    }
}
