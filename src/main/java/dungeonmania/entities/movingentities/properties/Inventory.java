package dungeonmania.entities.movingentities.properties;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Buildable;
import dungeonmania.interfaces.Defending;
import dungeonmania.interfaces.Durability;
import dungeonmania.interfaces.Storeable;

public class Inventory {

    List<Storeable> inventoryItems = new ArrayList<>();
    List<Buildable> buildableItems = new ArrayList<>();
    List<Attacking> attackingItems = new ArrayList<>();
    List<Defending> defendingItems = new ArrayList<>();
    List<Durability> weapons = new ArrayList<>();

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

    public List<Attacking> getAttackingItems() {
        return attackingItems;
    }

    public List<Defending> getDefendingItems() {
        return defendingItems;
    }

    public void updateWeaponsDurability() {
        for (Durability weapon : weapons) {
            weapon.reducedurability();
        }
    }

}
