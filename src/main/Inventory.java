package main;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<String> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void removeItem(String item) {
        items.remove(item);
    }

    public void showItems() {
        if (items.isEmpty()) {
            System.out.println("Inventaire vide.");
        } else {
            System.out.println("Inventaire :");
            for (String item : items) {
                System.out.println("- " + item);
            }
        }
    }

    public boolean contains(String item) {
        return items.contains(item);
    }

    public int size() {
        return items.size();
    }
}
