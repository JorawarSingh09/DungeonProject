package dungeonmania.goals;

import java.util.List;

import dungeonmania.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.properties.Inventory;
import dungeonmania.interfaces.Storeable;
import dungeonmania.entities.Entity;
import dungeonmania.entities.collectableentities.Treasure;

public class CollectTreasureGoal implements Goal {
    public int treasure;

    public CollectTreasureGoal(int treasure) {
        this.treasure = treasure;
    }

    public boolean isGoalCompleted(Dungeon dungeon) {
        Player player = dungeon.getPlayer();
        Inventory inventory = player.getInventory();
        int treasureCount = inventory.countItem(Treasure.class);

        return (treasureCount >= this.treasure);
    }

    public String toString(Dungeon dungeon) {
        if (isGoalCompleted(dungeon))
            return "";
        return ":treasure";
    }

}
