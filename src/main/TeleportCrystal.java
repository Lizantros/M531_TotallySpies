package main;

public class TeleportCrystal extends Item {
    public TeleportCrystal(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean isTakeable() {
        return true;
    }
}