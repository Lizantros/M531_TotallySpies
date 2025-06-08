package main;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (item != null) {
            this.items.add(item);
        }
    }

    public Item removeItemByName(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(itemName)) {
                return items.remove(i);
            }
        }
        return null;
    }

    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public Item getItemByName(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public String getItemsDisplay() {
        if (items.isEmpty()) {
            return "You're inventory is empty... You're broke...";
        }

        String displayText = "You have: ";

        for (int i = 0; i < items.size(); i++) {
            displayText += items.get(i).getName();
            if (i < items.size() - 1) {
                displayText += ", ";
            }
        }
        displayText += ".";
        return displayText;
    }
}