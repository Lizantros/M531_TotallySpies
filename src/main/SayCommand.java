package main;

public class SayCommand implements ICommand {
    @Override
    public String getVerb() {
        return "say";
    }

    @Override
    public String getDescription() {
        return "Fait parler le joueur.";
    }

    @Override
    public void execute(Game game, String[] args) {
        if (args.length < 2) {
            System.out.println("Utilisation : say <message>");
            return;
        }
        StringBuilder message = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            message.append(args[i]).append(" ");
        }
        System.out.println("Vous dites : " + message.toString().trim());
    }
}