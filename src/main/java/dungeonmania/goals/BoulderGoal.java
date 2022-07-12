package dungeonmania.goals;

import java.util.List;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

public class BoulderGoal implements Goal {

    public boolean isGoalCompleted(Dungeon dungeon) {
        List<Entity> switches = dungeon.getEntitiesOfType("FloorSwitch");
        for (Entity floorSwitch : switches) {
            boolean boulder = false;
            Position pos = floorSwitch.getPos();
            List<Entity> entitiesOnPos = dungeon.getEntitiesOnBlock(pos);
            for (Entity entity : entitiesOnPos) {
                if (entity.getType().equals("Boulder")) {
                    boulder = true;
                }
            }
            if (!boulder) {
                return false;
            }
        }
        return true;
    }

}
