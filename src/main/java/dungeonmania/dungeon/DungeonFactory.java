package dungeonmania.dungeon;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;
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
import dungeonmania.dungeon.LoadConfig;
import dungeonmania.util.FileLoader;
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
    private int currentDungeonID = -1;
    LoadConfig loadedConfig;

    public DungeonFactory() {

    }

    private int setDungeonID() {
        currentDungeonID++;
        return currentDungeonID;
    }

    public Dungeon createNewGame(String dungeonName, JsonObject dungeonMap, JsonObject config, String configName,
            Boolean isSaved) {
        // JsonObject dungeonMap = FileLoader.getDungeonFile(dungeonName);
        // JsonObject config = FileLoader.getDungeonFile(configName);
        this.loadedConfig = new LoadConfig(config, configName);

        return createDungeon(dungeonName, dungeonMap, isSaved, configName);
    }

    public Dungeon createSavedGame(String dungeonName) {

        return null;
    }

    private Dungeon createDungeon(String dungeonName, JsonObject dungeonMap, boolean isSaved, String configName) {
        Dungeon dungeon = new Dungeon(dungeonName, 1, configName);
        JsonObject goals = dungeonMap.get("goal-condition").getAsJsonObject();
        dungeon.setGoals(prepareGoals(goals));
        JsonArray entities = dungeonMap.get("entities").getAsJsonArray();
        makeEntities(entities, dungeon, isSaved);
        return dungeon;
    }

    public void makeEntities(JsonArray entities, Dungeon dungeon, boolean isSaved) {
        Map<String, List<Portal>> portals = new HashMap<>();
        // create portal, add it to hashmap
        // send hashMap to portal factory at the end
        for (JsonElement entity : entities) {
            int x = Integer.parseInt(((JsonObject) entity).get("x").toString());
            int y = Integer.parseInt(((JsonObject) entity).get("y").toString());

            String type = ((JsonObject) entity).get("type").getAsString();
            switch (type) {

                case "player":

                    Player player = new Player(setDungeonID(), new Position(x, y), false, false,
                            loadedConfig.player_attack, loadedConfig.player_health,
                            loadedConfig.bow_durability, loadedConfig.shield_durability, loadedConfig.shield_defence,
                            loadedConfig.midnight_armour_attack, loadedConfig.midnight_armour_defence,
                            loadedConfig.mind_control_duration);

                    dungeon.addEntity(player);
                    dungeon.setPlayer(player);
                    dungeon.setSpiderSpawner(
                            new SpiderSpawn(loadedConfig.spider_spawn_rate, new Position(x, y),
                                    loadedConfig.spider_attack,
                                    loadedConfig.spider_health));
                    if (isSaved) {
                        // // run saved player stuff here
                        // // add items to inventor
                        // // player.addItem(item);
                        JsonArray inventory = ((JsonObject) entity).get("inventory").getAsJsonArray();
                        for (JsonElement item : inventory) {

                            player.addItem(makeItem((JsonObject) item, dungeon, dungeon.getPlayer()));
                        }
                    }
                    break;

                case "wall":
                    dungeon.addEntity(new Wall(setDungeonID(), new Position(x, y), false, true));
                    break;
                case "exit":
                    dungeon.addEntity(new Exit(setDungeonID(), new Position(x, y), false, true));
                    break;
                case "boulder":
                    dungeon.addEntity(new Boulder(setDungeonID(), new Position(x, y), false, true));
                    break;
                case "switch":
                    dungeon.addEntity(new FloorSwitch(setDungeonID(), new Position(x, y), false, false));
                    break;
                case "door":
                    dungeon.addEntity(new Door(setDungeonID(), new Position(x, y),
                            Integer.parseInt(((JsonObject) entity).get("key").toString())));
                    break;
                case "portal":
                    String colour = ((JsonObject) entity).get("colour").toString();
                    Portal portal = new Portal(setDungeonID(), new Position(x, y), colour);
                    dungeon.addEntity(portal);
                    if (portals.containsKey(colour)) {
                        portals.get(colour).add(portal);
                    } else {
                        List<Portal> portalsPair = new ArrayList<>();
                        portalsPair.add(portal);
                        portals.put(colour, portalsPair);
                    }
                    break;
                case "zombie_toast_spawner":
                    dungeon.addEntity(new ZombieToastSpawner(setDungeonID(), new Position(x, y), true,
                            true, loadedConfig.zombie_spawn_rate, loadedConfig.zombie_attack,
                            loadedConfig.zombie_health));
                    break;
                case "spider":
                    dungeon.addEntity(new Spider(setDungeonID(), new Position(x, y), false, false,
                            loadedConfig.spider_attack, loadedConfig.spider_health));
                    break;
                case "zombie_toast":
                    dungeon.addEntity(new ZombieToast(setDungeonID(), new Position(x, y), false, false,
                            loadedConfig.zombie_attack, loadedConfig.zombie_health));
                    break;
                case "mercenary":
                    dungeon.addEntity(new Mercenary(setDungeonID(), new Position(x, y), true, false,
                            loadedConfig.ally_attack,
                            loadedConfig.ally_defence, loadedConfig.mercenary_attack, loadedConfig.mercenary_health,
                            loadedConfig.bribe_radius,
                            loadedConfig.bribe_amount));
                    break;
                case "treasure":
                    dungeon.addEntity(new Treasure(setDungeonID(), new Position(x, y), false, false));
                    break;
                case "key":
                    dungeon.addEntity(new Key(setDungeonID(), new Position(x, y),
                            Integer.parseInt(((JsonObject) entity).get("key").toString())));
                    break;
                case "invincibility_potion":
                    dungeon.addEntity(new InvincibilityPotion(setDungeonID(), new Position(x, y), false,
                            false, loadedConfig.invincibility_potion_duration));
                    break;
                case "invisibility_potion":
                    dungeon.addEntity(new InvisibilityPotion(setDungeonID(), new Position(x, y), false,
                            false, loadedConfig.invisibility_potion_duration));
                    break;
                case "wood":
                    dungeon.addEntity(new Wood(setDungeonID(), new Position(x, y), false, false));
                    break;
                case "arrow":
                    dungeon.addEntity(new Arrow(setDungeonID(), new Position(x, y), false, false));
                    break;
                case "bomb":
                    dungeon.addEntity(
                            new Bomb(setDungeonID(), new Position(x, y), false, false,
                                    loadedConfig.bomb_radius));
                    break;
                case "sword":
                    dungeon.addEntity(new Sword(setDungeonID(), new Position(x, y), false, false,
                            loadedConfig.sword_attack, loadedConfig.sword_durability));
                    break;
                case "bow":
                    dungeon.addEntity(new Bow(setDungeonID(), loadedConfig.bow_durability));
                    break;
                case "shield":
                    dungeon.addEntity(
                            new Shield(setDungeonID(), false, false, loadedConfig.shield_durability,
                                    loadedConfig.shield_defence));
                    break;

                case "swamp_tile":
                    SwampTile swampTile = new SwampTile(setDungeonID(), new Position(x, y),
                            Integer.parseInt(((JsonObject) entity).get("movement_factor").toString()));
                    dungeon.addEntity(swampTile);
                    dungeon.addSwampTile(swampTile);
                    break;

                case "sun_stone":
                    dungeon.addEntity(new Sunstone(setDungeonID(), new Position(x, y), false, false));
                    break;

                case "midnight_armour":
                    dungeon.addEntity(new MidnightArmour(setDungeonID(), loadedConfig.midnight_armour_attack,
                            loadedConfig.midnight_armour_defence));
                    break;

                case "sceptre":
                    dungeon.addEntity(new Sceptre(setDungeonID()));
                    break;
                case "assassin":
                    dungeon.addEntity(new Assassin(setDungeonID(), new Position(x, y), true, false,
                            loadedConfig.ally_attack, loadedConfig.ally_defence, loadedConfig.assassin_attack,
                            loadedConfig.assassin_health,
                            loadedConfig.bribe_radius, loadedConfig.assassin_bribe_amount,
                            loadedConfig.assassin_bribe_fail_rate,
                            loadedConfig.assassin_recon_radius));
                    break;
                case "hydra":
                    dungeon.addEntity(new Hydra(setDungeonID(), new Position(x, y), loadedConfig.hydra_attack,
                            loadedConfig.hydra_health, loadedConfig.hydra_health_increase_rate,
                            loadedConfig.hydra_health_increase_amount));
                    break;
            }
        }
        portalFactory(portals);
    }

    private Entity makeItem(JsonObject item, Dungeon dungeon, Player player) {
        String type = item.get("type").getAsString();
        int x = 0;
        int y = 0;
        switch (type) {
            case "treasure":
                return (new Treasure(setDungeonID(), new Position(x, y), false, false));

            case "key":
                return (new Key(setDungeonID(), new Position(x, y),
                        Integer.parseInt(((JsonObject) item).get("key").toString())));

            case "invincibility_potion":
                return (new InvincibilityPotion(setDungeonID(), new Position(x, y), false,
                        false, loadedConfig.invincibility_potion_duration));

            case "invisibility_potion":
                return (new InvisibilityPotion(setDungeonID(), new Position(x, y), false,
                        false, loadedConfig.invisibility_potion_duration));

            case "wood":
                return (new Wood(setDungeonID(), new Position(x, y), false, false));

            case "arrow":
                return (new Arrow(setDungeonID(), new Position(x, y), false, false));

            case "bomb":
                return (new Bomb(setDungeonID(), new Position(x, y), false, false,
                        loadedConfig.bomb_radius));

            case "sword":
                return (new Sword(setDungeonID(), new Position(x, y), false, false,
                        loadedConfig.sword_attack, loadedConfig.sword_durability));
            case "bow":
                return (new Bow(setDungeonID(), loadedConfig.bow_durability));
            case "shield":
                return (new Shield(setDungeonID(), false, false, loadedConfig.shield_durability,
                        loadedConfig.shield_defence));

        }
        return null;
    }

    private void portalFactory(Map<String, List<Portal>> portals) {
        for (List<Portal> portalPair : portals.values()) {
            portalPair.get(0).setPair(portalPair.get(1));
        }
    }

    public Goal prepareGoals(JsonObject goals) {
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
                    newGoal.addSubgoal(prepareGoals((JsonObject) subgoal));
                } else {
                    String subgoalString = ((JsonObject) subgoal).get("goal").getAsString();
                    newGoal.addSubgoal(goalType(subgoalString));
                }
            }
            return newGoal;
        } else {
            return goalType(goals.get("goal").getAsString());
        }
    }

    public Goal goalType(String goal) {
        // switch (goal) {
        // case "enemies":
        // return new EnemiesGoal(loadedConfig.enemy_goal);
        // case "boulders":
        // return new BoulderGoal();
        // case "treasure":
        // return new CollectTreasureGoal(loadedConfig.treasure_goal);
        // case "exit":
        // return new ExitGoal();
        // }
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
