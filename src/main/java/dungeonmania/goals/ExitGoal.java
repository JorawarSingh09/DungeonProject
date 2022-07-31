package dungeonmania.goals;

import java.util.List;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Position;

public class ExitGoal implements Goal {

    public boolean isGoalCompleted(Dungeon dungeon) {
        List<Entity> exits = dungeon.getEntitiesOfType("exit");
        Player player = dungeon.getPlayer();
        Position playerPos = player.getPosition();
        for (Entity exit : exits) {
            if (playerPos.equals(exit.getPosition())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(Dungeon dungeon) {
        if (isGoalCompleted(dungeon))
            return "";
        return ":exit";
    }

    @Override
    public JsonObject getJson(Dungeon dungeon) {
        JsonObject goal = new JsonObject();
        goal.addProperty("goal", this.toString(dungeon));
        return goal;
    }

}
