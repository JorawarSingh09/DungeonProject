package dungeonmania.entities.movingentities.properties;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.interfaces.Buildable;
import dungeonmania.interfaces.Storeable;

public class Inventory {
    List<Storeable> inventoryItems = new ArrayList<>();
    List<Buildable> buildableItems = new ArrayList<>();

    public Inventory(List<Storeable> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public List<Storeable> getInventoryItems() {
        return inventoryItems;
    }

    public List<Buildable> getBuildableItems() {
        // TO DO
        return buildableItems;
    }

    public void build() {
        // TO DO
    }
}
