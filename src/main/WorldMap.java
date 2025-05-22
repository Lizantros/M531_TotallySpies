package main;

import utils.IPrintable;
import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private Map<String, Location> locationsByName;
    private Location[][] grid;
    private int rows;
    private int columns;
    private int playerGridR;
    private int playerGridC;

    public WorldMap(int r, int c) {
        this.rows = r;
        this.columns = c;
        this.locationsByName = new HashMap<>();
        this.grid = new Location[rows][columns];
        this.playerGridR = -1;
        this.playerGridC = -1;
    }

    public void addLocationToMap(Location location, int r, int c) {
        if (r >= 0 && r < rows && c >= 0 && c < columns) {
            String key = location.getName().toLowerCase();
            locationsByName.put(key, location);
            grid[r][c] = location;
        } else {
            System.out.println("La zone " + location.getName() + " est quelque peu en dehors de la carte !!!! !!!!!!!!!!("+r+","+c+").");
        }
    }

    public Location getLocationByName(String name) {
        String key = name.toLowerCase();
        Location foundLocation = locationsByName.get(key);
        return foundLocation;
    }

    public void updatePlayerGridPosition(Location playerCurrentLocation) {
        for (int r_idx = 0; r_idx < rows; r_idx++) {
            for (int c_idx = 0; c_idx < columns; c_idx++) {
                if (grid[r_idx][c_idx] == playerCurrentLocation) {
                    this.playerGridR = r_idx;
                    this.playerGridC = c_idx;
                    return;
                }
            }
        }

        this.playerGridR = -1;
        this.playerGridC = -1;
    }

    public IPrintable[][] getGridForPrinting() {
        return grid;
    }

    public int getPlayerGridR() {
        return playerGridR;
    }

    public int getPlayerGridC() {
        return playerGridC;
    }
}