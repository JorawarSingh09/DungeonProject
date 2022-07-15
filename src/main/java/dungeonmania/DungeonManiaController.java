package dungeonmania;

import dungeonmania.controllers.EntityController;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.spawners.ZombieToastSpawner;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DungeonManiaController {

    private int currMaxDungeonId = 0;
    List<Dungeon> dungeonList = new ArrayList<>();
    Map<Integer, Dungeon> dungeons = new HashMap<>();

    public String getSkin() {
        return "default";
    }

    public String getLocalisation() {
        return "en_US";
    }

    /**
     * Returns a list of
     * /dungeons
     */
    public static List<String> dungeons() {
        return FileLoader.listFileNamesInResourceDirectory("dungeons");

    }

    /**
     * /configs
     */
    public static List<String> configs() {
        return FileLoader.listFileNamesInResourceDirectory("configs");
    }

    /**
     * /game/new
     */
    public DungeonResponse newGame(String dungeonName, String configName) throws IllegalArgumentException {
        Dungeon dungeon;
        if (!dungeons().contains(dungeonName) || !configs().contains(configName)) {
            throw new IllegalArgumentException();
        }
        try {
            String jsonDungeon = new String(FileLoader.loadResourceFile("dungeons/" + dungeonName + ".json"));
            JsonObject jsonObject = JsonParser.parseString(jsonDungeon).getAsJsonObject();
            JsonArray entitiesArray = jsonObject.get("entities").getAsJsonArray();
            JsonObject goals = jsonObject.get("goal-condition").getAsJsonObject();
            EntityController entityController = new EntityController();
            String jsonConfig = new String(FileLoader.loadResourceFile("configs/" + configName + ".json"));
            JsonObject configs = JsonParser.parseString(jsonConfig).getAsJsonObject();
            currMaxDungeonId += 1;
            dungeon = entityController.startGame(entitiesArray, goals, configs, currMaxDungeonId + 1, dungeonName);
            dungeons.put(currMaxDungeonId, dungeon);

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        return dungeon.createDungeonResponse();
    }

    /**
     * /game/dungeonResponseModel
     */
    public DungeonResponse getDungeonResponseModel() {
        return dungeons.get(1).createDungeonResponse();
    }

    /**
     * /game/tick/item
     */
    public DungeonResponse tick(String itemUsedId) throws IllegalArgumentException, InvalidActionException {
        int itemId = Integer.parseInt(itemUsedId);
        Dungeon dungeon = dungeons.get(1);
        if (!dungeon.itemInPlayerInventory(itemId))
            throw new InvalidActionException("item not in player inventory");
        if (!dungeon.itemIsUsable(itemId))
            throw new InvalidActionException("item not usable");
        dungeon.useItem(itemId);
        dungeon.tick(false);
        return dungeon.createDungeonResponse();
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        Dungeon dungeon = dungeons.get(1);
        dungeon.updateMovement(movementDirection);

        dungeon.tick(true);
        return dungeon.createDungeonResponse();
    }

    /**
     * /game/build
     */
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        Dungeon dungeon = dungeons.get(1);
        // If buildable is not one of bow, shield
        if (!buildable.equals("bow") && !buildable.equals("shield"))
            throw new IllegalArgumentException("not buildable item");
        // If the player does not have sufficient items to craft the buildable
        if (!dungeon.canBuild(buildable))
            throw new InvalidActionException("the player does not have sufficient items to craft the buildable");
        dungeon.build(buildable);
        dungeon.tick(false);
        return dungeon.createDungeonResponse();
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        Dungeon dungeon = dungeons.get(1);
        if (dungeon.getEntityById(Integer.parseInt(entityId)).getType().equals("mercenary")) {
            if (!dungeons.get(1).bribeMercenary((Mercenary) dungeon.getEntityById(Integer.parseInt(entityId)))) {
                throw new InvalidActionException("unable to bribe");
            }
            // tick dungeon
            dungeon.tick(false);
        }

        if (dungeon.getEntityById(Integer.parseInt(entityId)).getType().equals("zombie_toast_spawner")) {
            if (!dungeons.get(1)
                    .tryBreakZomSpawn((ZombieToastSpawner) dungeon.getEntityById(Integer.parseInt(entityId)))) {
                throw new InvalidActionException("Cannot break spawner");
            }
        }
        return dungeon.createDungeonResponse();
    }
}
