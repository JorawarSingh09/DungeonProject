package dungeonmania.goals;

import com.google.gson.JsonObject;

import dungeonmania.dungeon.Dungeon;

public interface Goal {
    
    public boolean isGoalCompleted(Dungeon dungeon);

    public String toString(Dungeon dungeon);

    public JsonObject getJson(Dungeon dungeon);

}
