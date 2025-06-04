package main;

import java.util.HashSet;
import java.util.Set;


public class Player {
    private Location currentLocation;
    private Inventory inventory;
    private Set<Location> visitedLocations = new HashSet<>();


    public Player(Location startingLocation) {
        this.currentLocation = startingLocation;
        this.inventory = new Inventory();
        this.visitedLocations = new HashSet<>();
        this.visitedLocations.add(startingLocation);
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addVisitedLocation(Location location) {
        visitedLocations.add(location);
    }

    public boolean hasVisited(Location location) {
        return visitedLocations.contains(location);
    }

    public Set<Location> getVisitedLocations() {
        return visitedLocations;
    }

}