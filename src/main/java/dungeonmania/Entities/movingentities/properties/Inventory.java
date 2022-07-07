package dungeonmania.Entities.movingentities.properties;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.interfaces.InventoryItem;

public class Inventory {
    List<InventoryItem> inventoryItems = new ArrayList<>();

    public Inventory(List<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    public List<InventoryItem> getBuildableItems() {
        // TO DO
        return inventoryItems;
    }

    public void build() {
        // TO DO
    }
}
