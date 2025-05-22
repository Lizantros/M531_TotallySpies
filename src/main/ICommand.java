package main;

public interface ICommand {
    String getVerb();
    String getDescription();
    void execute(Game game, String[] args);
}