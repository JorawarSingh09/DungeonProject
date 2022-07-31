package dungeonmania.dungeon;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dungeonmania.entities.EntityFactory;
import dungeonmania.goals.BoulderGoal;
import dungeonmania.goals.CollectTreasureGoal;
import dungeonmania.goals.CompletedGoal;
import dungeonmania.goals.ComplexGoal;
import dungeonmania.goals.EnemiesGoal;
import dungeonmania.goals.ExitGoal;
import dungeonmania.goals.Goal;
import dungeonmania.goals.GoalCondition;

import dungeonmania.util.LoadConfig;

public class DungeonFactory {

    public static Dungeon createDungeon(String dungeonName, int dungeonID, JsonObject dungeonMap, boolean isSaved,
            LoadConfig loadedConfig) {
        Dungeon dungeon = new Dungeon(dungeonName, dungeonID, loadedConfig);
        // dungeon.setCurrmaxEntityId(loadedConfig.currMaxEntityId);
        JsonObject goals = dungeonMap.get("goal-condition").getAsJsonObject();
        dungeon.setGoals(prepareGoals(goals, loadedConfig));
        JsonArray entities = dungeonMap.get("entities").getAsJsonArray();
        EntityFactory.makeEntities(entities, dungeon, isSaved, loadedConfig);
        return dungeon;
    }

    private static Goal prepareGoals(JsonObject goals, LoadConfig config) {
        if (goals.has("subgoals")) {
            GoalCondition condition;
            if (goals.get("goal").getAsString().equals("OR")) {
                condition = GoalCondition.OR;
            } else {
                condition = GoalCondition.AND;
            }
            ComplexGoal newGoal = new ComplexGoal(condition);
            JsonArray subgoals = goals.get("subgoals").getAsJsonArray();
            for (JsonElement subgoal : subgoals) {
                if (subgoal.isJsonObject() && ((JsonObject) subgoal).has("subgoals")) {
                    newGoal.addSubgoal(prepareGoals((JsonObject) subgoal, config));
                } else {
                    String subgoalString = ((JsonObject) subgoal).get("goal").getAsString();
                    newGoal.addSubgoal(goalType(subgoalString, config));
                }
            }
            return newGoal;
        } else {
            return goalType(goals.get("goal").getAsString(), config);
        }
    }

    private static Goal goalType(String goal, LoadConfig loadedConfig) {

        if (goal.contains("enemies")) {
            return new EnemiesGoal(loadedConfig.enemy_goal);
        } else if (goal.contains("boulders")) {
            return new BoulderGoal();
        } else if (goal.contains("treasure")) {
            return new CollectTreasureGoal(loadedConfig.treasure_goal);
        } else if (goal.contains("exit")) {
            return new ExitGoal();
        }
        return new CompletedGoal();
    }
}
