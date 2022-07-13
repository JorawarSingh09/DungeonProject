package dungeonmania;

import dungeonmania.controllers.EntityController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
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
            // Files.readAllBytes(Paths.get("src/main/resources/dungeons/" + dungeonName +
            // ".json")));
            JsonObject jsonObject = JsonParser.parseString(jsonDungeon).getAsJsonObject();
            JsonArray entitiesArray = jsonObject.get("entities").getAsJsonArray();
            JsonObject goals = jsonObject.get("goal-condition").getAsJsonObject();
            EntityController entityController = new EntityController();
            String jsonConfig = new String(FileLoader.loadResourceFile("configs/" + configName + ".json"));
            // Files.readAllBytes(Paths.get("src/main/resources/configs/" + configName +
            // ".json")));
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
        return dungeon.createDungeonResponse();
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        Dungeon dungeon = dungeons.get(1);
        dungeon.updateMovement(movementDirection);
        
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
        return dungeon.createDungeonResponse();
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

}
