package dungeonmania.entities.movingentities.properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Storeable getItemFromId(int id) {
        return items.get(id);
    }

    public boolean hasItem(int id) {
        return items.containsKey(id);
    }

    public List<Storeable> getInventoryItems() {
        return inventoryItems;
    }

    public List<String> getBuildableItems() {
        List<String> buildables = new ArrayList<>();
        int wood = countItem(Wood.class);
        int arrows = countItem(Arrow.class);
        int treasure = countItem(Treasure.class);
        int key = countItem(Key.class);;
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
                Shield shield = new Shield(nextItemMaxId, false, false, shieldDurability, 2);
                inventoryItems.add(shield);
                buildableItems.add(shield);
                weapons.add(shield);
                defendingItems.add(shield);
                removeShieldItems();
                break;
            case "bow":
                Bow bow = new Bow(nextItemMaxId, bowDurability);
                inventoryItems.add(bow);
                buildableItems.add(bow);
                weapons.add(bow);
                attackingItems.add(bow);
                removeBowItems();
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

    private int countItem(Class<?> t) {
        int count = 0;
        for (Storeable item : inventoryItems) {
            if (item.getClass().equals(t)) {
                count += 1;
            }
        }
        return count;
    }

    private void removeShieldItems() {
        removeItem(2, Wood.class);
        if (!removeItem(1, Treasure.class)) {
            removeItem(1, Key.class);
        }
    }

    private void removeBowItems() {
        removeItem(1, Wood.class);
        removeItem(3, Arrow.class);
    }

    private boolean removeItem(int removeAmount, Class<?> t) {
        int itemRemoved = 0;
        ListIterator<Storeable> inventory = inventoryItems.listIterator();
        while(inventory.hasNext()) {
            if (inventory.next().getClass().equals(t) && itemRemoved < removeAmount) {
                inventory.remove();
                itemRemoved += 1;
            }
        }
        List<Integer> remainingIds = inventoryItems.stream().map(Storeable::getItemId).collect(Collectors.toList());
        for (Integer key : items.keySet()) {
            if (!remainingIds.contains(key)) items.remove(key);
        }
        return (itemRemoved == removeAmount);
    }

    public void removeItemById(int id) {
        inventoryItems.remove(items.get(id));
        items.remove(id);
    }

}
