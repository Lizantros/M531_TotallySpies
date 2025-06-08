package main;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private Location currentLocation;
    private Inventory inventory;
    private Set<Location> visitedLocations;

    public Player(Location startingLocation) {
        this.currentLocation = startingLocation;
        this.inventory = new Inventory();
        this.visitedLocations = new HashSet<>();
        if (startingLocation != null) {
            this.visitedLocations.add(startingLocation);
        }
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
        if (location != null) {
            this.visitedLocations.add(location);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean hasVisited(Location location) {
        return visitedLocations.contains(location);
    }

    public Set<Location> getVisitedLocations() {
        return new HashSet<>(visitedLocations);
    }
}