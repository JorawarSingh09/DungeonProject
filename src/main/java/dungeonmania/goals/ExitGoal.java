package dungeonmania.goals;

import java.util.List;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Position;;

public class ExitGoal implements Goal {

    public boolean isGoalCompleted(Dungeon dungeon) {
        List<Entity> exits = dungeon.getEntitiesOfType("Exit");
        Player player = dungeon.getPlayer();
        Position playerPos = player.getPos();
        for (Entity exit : exits) {
            if (playerPos.equals(exit.getPos())) {
                return true;
            }
        }
        return false;
    }

}
