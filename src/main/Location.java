package main;

import utils.IPrintable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location implements IPrintable {
    private String name;
    private String descriptionText;
    private boolean locked;
    private Map<String, String> exits;
    private List<Item> items;

    private String enigmaQuestion;
    private String enigmaSolution;
    private Key keyReward;
    private boolean enigmaSolved;
    private String enigmaIndicatorObjectName;


    public Location(String name, String descriptionText) {
        this.name = name;
        this.descriptionText = descriptionText;
        this.locked = false;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.enigmaSolved = false;
    }

    public String getName() {
        return name;
    }

    public String getFullDescription() {
        String fullDesc = "You're lost in : " + name + "\n" + descriptionText;
        if (items != null && !items.isEmpty()) {
            fullDesc += "\nYou're seeing... : ";
            List<String> itemNames = new ArrayList<>();
            for (Item item : items) {
                itemNames.add(item.getName());
            }
            fullDesc += String.join(", ", itemNames) + ".";
        }
        if (exits != null && !exits.isEmpty()) {
            fullDesc += "\nExit maybe potentially existing : ";
            List<String> exitDirections = new ArrayList<>(exits.keySet());
            Collections.sort(exitDirections);
            fullDesc += String.join(" ", exitDirections);
        } else {
            fullDesc += "\nOh... I am in trouble... because the game creator made a mistake... Oh noooo";
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
        if (exits == null) return null;
        return this.exits.get(direction.toLowerCase());
    }

    public Map<String, String> getExits() {
        if (exits == null) return new HashMap<>();
        return new HashMap<>(exits);
    }

    public void addItem(Item item) {
        if (item != null) {
            if (this.items == null) {
                this.items = new ArrayList<>();
            }
            this.items.add(item);
        }
    }

    public Item removeItemByName(String itemName) {
        if (items == null || itemName == null) return null;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(itemName)) {
                return items.remove(i);
            }
        }
        return null;
    }

    public List<Item> getItems() {
        if (items == null) return new ArrayList<>();
        return new ArrayList<>(this.items);
    }

    public void setEnigma(String question, String solution, Key keyReward, String enigmaIndicatorName) {
        this.enigmaQuestion = question;
        this.enigmaSolution = solution.toLowerCase();
        this.keyReward = keyReward;
        this.enigmaIndicatorObjectName = enigmaIndicatorName;
        this.enigmaSolved = false;
    }

    public boolean hasEnigma() {
        return this.enigmaSolution != null;
    }

    public boolean isEnigmaSolved() {
        return this.enigmaSolved;
    }

    public boolean solveEnigma(String playerSolution) {
        if (hasEnigma() && !isEnigmaSolved()) {
            if (playerSolution != null && playerSolution.toLowerCase().equals(this.enigmaSolution)) {
                this.enigmaSolved = true;
                System.out.println("YES YES YES You provided the GOOD answer");
                if (this.keyReward != null) {
                    this.addItem(this.keyReward);
                    System.out.println(this.keyReward.getName() + " not so mysteriously appear !");
                }
                if (this.enigmaIndicatorObjectName != null && !this.enigmaIndicatorObjectName.isEmpty()) {
                    Item indicator = removeItemByName(this.enigmaIndicatorObjectName);
                    if (indicator != null) {
                        System.out.println(indicator.getName() + " Disapear...");
                    }
                }
                return true;
            } else {
                System.out.println("My mom once said : Think more before talking");
                return false;
            }
        } else if (isEnigmaSolved()) {
            System.out.println("You already resolved it... Please, play this game seriously");
        }
        return false;
    }

    @Override
    public String getPrintableString() {
        return name;
    }

    @Override
    public boolean isGrayedOut() {
        return this.locked;
    }

    @Override
    public String toString() {
        return getPrintableString();
    }
}