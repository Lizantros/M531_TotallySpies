package main;

public class Enigma extends Item {
    public Enigma(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean isTakeable() {
        return false;
    }
}