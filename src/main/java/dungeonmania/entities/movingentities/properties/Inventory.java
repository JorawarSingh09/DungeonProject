package dungeonmania.entities.movingentities.properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.reflections.Store;

import dungeonmania.entities.buildableentities.Bow;
import dungeonmania.entities.buildableentities.Shield;
import dungeonmania.entities.collectableentities.Arrow;
import dungeonmania.entities.collectableentities.Key;
import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.entities.collectableentities.Wood;
import dungeonmania.interfaces.Attacking;
import dungeonmania.interfaces.Buildable;
import dungeonmania.interfaces.Defending;
import dungeonmania.interfaces.Durability;
import dungeonmania.interfaces.Storeable;
import dungeonmania.util.Position;

public class Inventory {

    List<Storeable> inventoryItems = new ArrayList<>();
    List<Buildable> buildableItems = new ArrayList<>();
    List<Attacking> attackingItems = new ArrayList<>();
    List<Defending> defendingItems = new ArrayList<>();
    List<Durability> weapons = new ArrayList<>();
    Map<Integer, Storeable> items = new HashMap<>();
    private int bowDurability;
    private int shieldDurability;
    private Position playerPos;    

    public Inventory(int bowDurability, int shieldDurability, Position playerPos) {
        this.bowDurability = bowDurability;
        this.shieldDurability = shieldDurability;
    }

    public void addItem(Storeable item) {
        inventoryItems.add(item);
        if (item instanceof Attacking) {
            attackingItems.add((Attacking) item);
        }
        if (item instanceof Defending) {
            defendingItems.add((Defending) item);
        }
        if (item instanceof Durability) {
            weapons.add((Durability) item);
        }
        items.put(item.getItemId(), item);
    }

    public List<Storeable> getInventoryItems() {
        return inventoryItems;
    }

    public List<String> getBuildableItems() {
        List<String> buildables = new ArrayList<>();
        int wood = countWood();
        int arrows = countArrow();
        int treasure = countTreasure();
        int key = countKey();
        if ((key >= 1 || treasure >= 1) && (wood >= 2)) {
            buildables.add("shield");
        } else if (wood >= 1 && arrows >= 3) {
            buildables.add("bow");
        }
        return buildables;
    }

    public void build(String itemBuild, int nextItemMaxId) {
        switch(itemBuild) {
            case "shield":
                Shield shield = new Shield(nextItemMaxId, playerPos.getX(), playerPos.getY(), false, 
                                            false, shieldDurability, 2);
                inventoryItems.add(shield);
                buildableItems.add(shield);
                weapons.add(shield);
                defendingItems.add(shield);
                break;
            case "bow":
                Bow bow = new Bow(nextItemMaxId, playerPos.getX(), playerPos.getY(), false, false, bowDurability);
                inventoryItems.add(bow);
                buildableItems.add(bow);
                weapons.add(bow);
                attackingItems.add(bow);
                break;
        }
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

    private int countWood() {
        int count = 0;
        for (Storeable item : inventoryItems) {
            if (item instanceof Wood) {
                count += 1;
            }
        }
        return count;
    }

    private int countArrow() {
        int count = 0;
        for (Storeable item : inventoryItems) {
            if (item instanceof Arrow) {
                count += 1;
            }
        }
        return count;
    }

    private int countTreasure() {
        int count = 0;
        for (Storeable item : inventoryItems) {
            if (item instanceof Treasure) {
                count += 1;
            }
        }
        return count;
    }

    private int countKey() {
        int count = 0;
        for (Storeable item : inventoryItems) {
            if (item instanceof Key) {
                count += 1;
            }
        }
        return count;
    }

    private void removeShieldItems() {
        
    }

}
