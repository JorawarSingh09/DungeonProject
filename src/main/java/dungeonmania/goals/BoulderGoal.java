package dungeonmania.goals;

import org.json.JSONObject;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.enums.GoalString;

public class BoulderGoal implements Goal {

    public boolean isGoalCompleted(Dungeon dungeon) {
        return dungeon.areAllSwitchesTriggered();
    }

    @Override
    public String toString(Dungeon dungeon) {
        if (isGoalCompleted(dungeon))
            return GoalString.COMPLETED.toString();
        return GoalString.BOULDERGOAL.toString();
    }

    @Override
    public JsonObject getJson(Dungeon dungeon) {

        JsonObject goal = new JsonObject();
        goal.addProperty("goal", this.toString(dungeon));
        return goal;

    }
}
