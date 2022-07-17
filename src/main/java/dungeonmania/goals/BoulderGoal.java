package dungeonmania.goals;

import dungeonmania.Dungeon;
import dungeonmania.enums.GoalString;

public class BoulderGoal implements Goal {

    public boolean isGoalCompleted(Dungeon dungeon) {
        return dungeon.areAllSwitchesTriggered();
    }

    public String toString(Dungeon dungeon) {
        if (isGoalCompleted(dungeon))
            return GoalString.COMPLETED.toString();
        return GoalString.BOULDERGOAL.toString();
    }
}
