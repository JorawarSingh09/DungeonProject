package dungeonmania.dungeon;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.buildableentities.Bow;
import dungeonmania.entities.buildableentities.MidnightArmour;
import dungeonmania.entities.buildableentities.Sceptre;
import dungeonmania.entities.buildableentities.Shield;
import dungeonmania.entities.collectableentities.Arrow;
import dungeonmania.entities.collectableentities.Bomb;
import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.collectableentities.Key;
import dungeonmania.entities.collectableentities.Sunstone;
import dungeonmania.entities.collectableentities.Sword;
import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.entities.collectableentities.Wood;
import dungeonmania.entities.movingentities.Assassin;
import dungeonmania.entities.movingentities.Hydra;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.Spider;
import dungeonmania.entities.movingentities.ZombieToast;
import dungeonmania.entities.staticentities.Boulder;
import dungeonmania.entities.staticentities.Door;
import dungeonmania.entities.staticentities.Exit;
import dungeonmania.entities.staticentities.FloorSwitch;
import dungeonmania.entities.staticentities.Portal;
import dungeonmania.entities.staticentities.SwampTile;
import dungeonmania.entities.staticentities.Wall;
import dungeonmania.goals.BoulderGoal;
import dungeonmania.goals.CollectTreasureGoal;
import dungeonmania.goals.ComplexGoal;
import dungeonmania.goals.EnemiesGoal;
import dungeonmania.goals.ExitGoal;
import dungeonmania.goals.Goal;
import dungeonmania.goals.GoalCondition;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.spawners.SpiderSpawn;
import dungeonmania.spawners.ZombieToastSpawner;
import dungeonmania.util.FileLoader;
import dungeonmania.util.LoadConfig;
import dungeonmania.util.Position;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DungeonFactory {

    public static Dungeon createDungeon(String dungeonName, int dungeonID, JsonObject dungeonMap, boolean isSaved, LoadConfig loadedConfig) {
        Dungeon dungeon = new Dungeon(dungeonName, dungeonID, loadedConfig);
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
        return null;
    }
}
