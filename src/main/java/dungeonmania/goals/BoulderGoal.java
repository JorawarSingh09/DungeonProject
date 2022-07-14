package dungeonmania.goals;

import dungeonmania.Dungeon;

public class BoulderGoal implements Goal {

    public boolean isGoalCompleted(Dungeon dungeon) {
        return dungeon.areAllSwitchesTriggered();
    }

    public String toString(Dungeon dungeon) {
        if (isGoalCompleted(dungeon))
            return "";
        return ":boulders";
    }
}
