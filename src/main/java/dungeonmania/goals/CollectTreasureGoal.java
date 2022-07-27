package dungeonmania.goals;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.properties.Inventory;
import dungeonmania.entities.collectableentities.Sunstone;
import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.enums.GoalString;

public class CollectTreasureGoal implements Goal {
    private int treasure;

    public CollectTreasureGoal(int treasure) {
        this.treasure = treasure;
    }

    public boolean isGoalCompleted(Dungeon dungeon) {
        Player player = dungeon.getPlayer();
        Inventory inventory = player.getInventory();
        int treasureCount = (inventory.countItem(Treasure.class) + inventory.countItem(Sunstone.class));

        return (treasureCount >= this.treasure);
    }

    @Override
    public String toString(Dungeon dungeon) {
        if (isGoalCompleted(dungeon))
            return GoalString.COMPLETED.toString();;
        return GoalString.TREASURE.toString();
    }

    @Override
    public JsonObject getJson(Dungeon dungeon) {

        JsonObject goal = new JsonObject();
        goal.addProperty("goal", toString(dungeon));
        return goal;

    }

}
