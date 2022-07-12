package dungeonmania.goals;

import java.util.List;

import dungeonmania.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.properties.Inventory;
import dungeonmania.interfaces.Storeable;
import dungeonmania.entities.Entity;

public class CollectTreasureGoal implements Goal {
    public int treasure;

    public CollectTreasureGoal(int treasure) {
        this.treasure = treasure;
    }

    public boolean isGoalCompleted(Dungeon dungeon) {
        Player player = dungeon.getPlayer();
        Inventory inventory = player.getInventory();
        List<Storeable> items = inventory.getInventoryItems();
        int treasureCount = 0;
        for (Storeable item : items) {
            Entity i = (Entity) item;
            if (i.getType().equals("Treasure")) {
                treasureCount += 1;
            }
        }

        if (treasureCount >= this.treasure) {
            return true;
        } else {
            return false;
        }
    }

}
