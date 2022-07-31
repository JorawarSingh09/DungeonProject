package dungeonmania.goals;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;

public class CompletedGoal implements Goal {
    
    public boolean isGoalCompleted(Dungeon dungeon) {
        return true;
    }

    @Override
    public String toString(Dungeon dungeon) {
        return "";
    }

    @Override
    public JsonObject getJson(Dungeon dungeon) {
        JsonObject goal = new JsonObject();
        goal.addProperty("goal", this.toString(dungeon));
        return goal;
    }

}
