package main;

import utils.IPrintable;
import java.util.HashMap;
import java.util.Map;

public class Location implements IPrintable { //
    private String name;
    private String descriptionText; // description de la zone
    private boolean locked;
    private Map<String, String> exits; // liste des sortie possibles de la zone

    public Location(String name, String descriptionText) {
        this.name = name;
        this.descriptionText = descriptionText;
        this.locked = false;
        this.exits = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getFullDescription() {
        String fullDesc = "Tu es perdu dans : " + name + "\n" + descriptionText;
        if (!exits.isEmpty()) {
            fullDesc += "\nSorties potentiellement existantes : ";
            for (String dir : exits.keySet()) {
                fullDesc += dir + " ";
            }
        } else {
            fullDesc += "\n Oh... Je suis foutu...";
        }
        return fullDesc;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void addExit(String direction, String locationName) {
        this.exits.put(direction.toLowerCase(), locationName);
    }

    public String getExit(String direction) {
        return this.exits.get(direction.toLowerCase());
    }

    @Override
    public String getPrintableString() { //
        return "O";
    }

    @Override
    public boolean isGrayedOut() { //
        return this.locked;
    }

    @Override
    public String toString() {
        return getPrintableString();
    }
}