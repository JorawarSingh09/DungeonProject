package dungeonmania.entities.movingentities.properties;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.interfaces.Storeable;

public class Inventory {
    List<Storeable> inventoryItems = new ArrayList<>();

    public Inventory(List<Storeable> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public List<Storeable> getInventoryItems() {
        return inventoryItems;
    }

    public List<Storeable> getBuildableItems() {
        // TO DO
        return inventoryItems;
    }

    public void build() {
        // TO DO
    }
}
