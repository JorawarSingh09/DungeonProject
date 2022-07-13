package dungeonmania.goals;

import java.util.List;

import dungeonmania.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.staticentities.FloorSwitch;
import dungeonmania.entities.staticentities.Boulder;
import dungeonmania.interfaces.Static;

public class BoulderGoal implements Goal {

    public boolean isGoalCompleted(Dungeon dungeon) {
        return dungeon.areAllSwitchesTriggered();
    }

    public String toString(Dungeon dungeon) {
        if (isGoalCompleted(dungeon))
            return "";
        return ":boulder";
    }
}
