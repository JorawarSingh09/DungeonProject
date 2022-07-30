package dungeonmania.entities.movingentities.properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dungeonmania.entities.buildableentities.Bow;
import dungeonmania.entities.buildableentities.Buildable;
import dungeonmania.entities.buildableentities.Shield;
import dungeonmania.entities.buildableentities.MidnightArmour;
import dungeonmania.entities.buildableentities.Sceptre;
import dungeonmania.entities.collectableentities.Arrow;
import dungeonmania.entities.collectableentities.Key;
import dungeonmania.entities.collectableentities.Sunstone;
import dungeonmania.entities.collectableentities.Sword;
import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.entities.collectableentities.Wood;
import dungeonmania.entities.collectableentities.interfaces.Attacking;
import dungeonmania.entities.collectableentities.interfaces.Defending;
import dungeonmania.entities.collectableentities.interfaces.Durability;
import dungeonmania.entities.collectableentities.interfaces.Storeable;

public class Inventory {

    List<Storeable> inventoryItems = new ArrayList<>();
    List<Buildable> buildableItems = new ArrayList<>();
    List<Attacking> attackingItems = new ArrayList<>();
    List<Defending> defendingItems = new ArrayList<>();
    List<Durability> weapons = new ArrayList<>();
    Map<Integer, Storeable> items = new HashMap<>();
    public Map<Integer, String> itemHistory = new HashMap<>();
    private int bowDurability;
    private int shieldDurability;
    private int shieldDefence;
    private int armourAttack;
    private int armourDefence;
    private int mindControlDuration;

    // private Position playerPos;

    public Inventory(int bowDurability, int shieldDurability, int shieldDefence, int armourAttack, int armourDefence,
            int mindControlDuration) {
        this.bowDurability = bowDurability;
        this.shieldDurability = shieldDurability;
        this.shieldDefence = shieldDefence;
        this.armourAttack = armourAttack;
        this.armourDefence = armourDefence;
        this.mindControlDuration = mindControlDuration;
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
        itemHistory.put(item.getItemId(), item.getType());

    }

    public String getHistoricalItemType(int id) {
        return itemHistory.get(id);
    }

    public Storeable getItemFromId(int id) {
        return items.get(id);
    }

    public boolean hasItem(int id) {
        return items.containsKey(id);
    }

    public List<Storeable> getKeys() {
        return inventoryItems.stream()
                .filter(e -> e.getType().equals("key"))
                .collect(Collectors.toList());
    }

    public List<Storeable> getInventoryItems() {
        return inventoryItems;
    }

    public List<String> getBuildableItems(boolean hasZombie) {
        List<String> buildables = new ArrayList<>();
        int wood = countItem(Wood.class);
        int arrows = countItem(Arrow.class);
        int treasure = countItem(Treasure.class);
        int key = countItem(Key.class);
        int sword = countItem(Sword.class);
        int sunstone = countItem(Sunstone.class);

        if ((hasSunStone() || key >= 1 || treasure >= 1) && (wood >= 2)) {
            buildables.add("shield");
        }
        if (wood >= 1 && arrows >= 3) {
            buildables.add("bow");
        }
        if (!hasZombie && sword >= 1 && sunstone >= 1) {
            buildables.add("midnight_armour");
        } else if ((wood >= 1 || arrows >= 2) && (key >= 1 || treasure >= 1) && sunstone >= 1) {
            buildables.add("sceptre");
        }
        return buildables;
    }

    public void build(String itemBuild, int nextItemMaxId) {
        switch (itemBuild) {
            case "shield":
                Shield shield = new Shield(nextItemMaxId, shieldDurability, shieldDefence);
                addItem(shield);
                removeShieldItems();
                break;
            case "bow":
                Bow bow = new Bow(nextItemMaxId, bowDurability);
                addItem(bow);
                removeBowItems();
                break;
            case "midnight_armour":
                MidnightArmour armour = new MidnightArmour(nextItemMaxId, armourAttack, armourDefence);
                addItem(armour);
                removeArmourItems();
                break;
            case "sceptre":
                Sceptre sceptre = new Sceptre(nextItemMaxId);
                inventoryItems.add(sceptre);
                buildableItems.add(sceptre);
                removeSceptreItems();
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
        List<Integer> idsToRemove = new ArrayList<>();
        for (Durability weapon : weapons) {
            weapon.reduceDurability();
            if (weapon.getDurability() <= 0) {
                removeItemById(weapon.getItemId());
                idsToRemove.add(weapon.getItemId());
            }
        }
        attackingItems.removeIf(w -> idsToRemove.contains(w.getItemId()));
        defendingItems.removeIf(w -> idsToRemove.contains(w.getItemId()));
        weapons.removeIf(w -> idsToRemove.contains(w.getItemId()));
    }

    public int countItem(Class<?> t) {
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
        if (!hasSunStone()) {
            if (!removeItem(1, Treasure.class)) {
                removeItem(1, Key.class);
            }
        }
    }

    private void removeBowItems() {
        removeItem(1, Wood.class);
        removeItem(3, Arrow.class);
    }

    private void removeArmourItems() {
        removeItem(1, Sword.class);
        removeWeapon(1, Sword.class);
        removeItem(1, Sunstone.class);
    }

    private void removeSceptreItems() {
        if (countItem(Wood.class) >= 1) {
            removeItem(1, Wood.class);
        } else {
            removeItem(2, Arrow.class);
        }
        if (countItem(Key.class) >= 1) {
            removeItem(1, Key.class);
        } else {
            removeItem(1, Treasure.class);
        }
        removeItem(1, Sunstone.class);
    }

    private void removeWeapon(int removeAmount, Class<?> t) {
        int itemRemoved = 0;
        List<Attacking> toRemove = new ArrayList<>();
        if (Attacking.class.isAssignableFrom(t)) {
            for (Attacking attackItem : attackingItems) {
                if (attackItem.getClass().equals(t) && itemRemoved < removeAmount) {
                    toRemove.add(attackItem);
                    itemRemoved++;
                }
            }
        }
        attackingItems.removeAll(toRemove);
        itemRemoved = 0;
        List<Defending> toRemoveDefend = new ArrayList<>();
        if (Defending.class.isAssignableFrom(t)) {
            for (Defending defendItem : defendingItems) {
                if (defendItem.getClass().equals(t) && itemRemoved < removeAmount) {
                    toRemoveDefend.add(defendItem);
                    itemRemoved++;
                }
            }
        }
        defendingItems.removeAll(toRemoveDefend);
        itemRemoved = 0;
        List<Durability> toRemoveDur = new ArrayList<>();
        if (Durability.class.isAssignableFrom(t)) {
            for (Durability durItem : weapons) {
                if (durItem.getClass().equals(t) && itemRemoved < removeAmount) {
                    toRemoveDur.add(durItem);
                    itemRemoved++;
                }
            }
        }
        weapons.removeAll(toRemoveDur);
    }

    public boolean removeItem(int removeAmount, Class<?> t) {
        int itemRemoved = 0;
        List<Storeable> toRemove = new ArrayList<>();

        for (Storeable item : inventoryItems) {
            if (item.getClass().equals(t)
                    && itemRemoved < removeAmount) {
                toRemove.add(item);
                itemRemoved++;
            }
        }
        inventoryItems.removeAll(toRemove);

        List<Integer> remainingIds = inventoryItems.stream().map(Storeable::getItemId).collect(Collectors.toList());
        List<Integer> keys = new ArrayList<>();
        keys.addAll(items.keySet());
        for (Integer key : keys) {
            if (!remainingIds.contains(key))
                items.remove(key);
        }
        return (itemRemoved == removeAmount);
    }

    public void removeItemById(int id) {
        inventoryItems.remove(items.get(id));
        items.remove(id);
    }

    public boolean hasRightKey(int keyPair) {
        boolean foundKey = false;
        Storeable foundItem = null;
        for (Storeable item : inventoryItems) {
            if (item instanceof Key) {
                if (((Key) item).getKeyPair() == keyPair) {
                    foundKey = true;
                    foundItem = item;
                    break;
                }
            }
        }
        if (foundKey)
            removeItemById(foundItem.getItemId());
        return foundKey;
    }

    public boolean hasSunStone() {
        return (countItem(Sunstone.class) > 0);
    }

    public int getMindControlDuration() {
        return this.mindControlDuration;
    }

}
