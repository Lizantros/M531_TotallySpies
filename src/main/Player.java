package main;

public class Player {
    private Location currentLocation;

    public Player(Location startingLocation) {
        this.currentLocation = startingLocation;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }
}