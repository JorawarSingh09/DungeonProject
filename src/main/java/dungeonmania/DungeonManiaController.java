package dungeonmania;

// import dungeonmania.controllers.EntityController;
import dungeonmania.enums.ErrorString;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;
import dungeonmania.util.GameFile;
import dungeonmania.util.LoadConfig;
import dungeonmania.dungeon.Dungeon;
import dungeonmania.dungeon.DungeonFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

public class DungeonManiaController {
    // private String configFileName;
    private int currMaxDungeonId = 0;
    Dungeon dungeon;

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

    public static List<String> saves() {
        return FileLoader.listFileNamesInResourceDirectory("saves");

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
        if (!dungeons().contains(dungeonName) || !configs().contains(configName)) {
            throw new IllegalArgumentException();
        }
        try {
            String jsonDungeon = new String(FileLoader.loadResourceFile("dungeons/" + dungeonName + ".json"));
            JsonObject jsonObject = JsonParser.parseString(jsonDungeon).getAsJsonObject();

            String jsonConfig = new String(FileLoader.loadResourceFile("configs/" + configName + ".json"));
            JsonObject configs = JsonParser.parseString(jsonConfig).getAsJsonObject();

            LoadConfig config = new LoadConfig(configs, configName);
            dungeon = DungeonFactory.createDungeon(dungeonName, currMaxDungeonId, jsonObject, false, config);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        return dungeon.createDungeonResponse();
    }

    /**
     * /game/save
     */
    public DungeonResponse saveGame(String name) throws IllegalArgumentException {
        GameFile.saveDungeon(dungeon);
        return getDungeonResponseModel();
    }

    /**
     * /game/load
     */
    public DungeonResponse loadGame(String name) throws IllegalArgumentException {
        if (!saves().contains(name)) {
            System.out.println("save not found");
            throw new IllegalArgumentException();
        }
        try {
            String savedFile = new String(
                    FileLoader.loadResourceFile("saves/" + name + ".json"));
            JsonObject gameFile = JsonParser.parseString(savedFile).getAsJsonObject();

            JsonObject config = gameFile.get("config").getAsJsonObject();
            LoadConfig loadedConfig = new LoadConfig(config, 
                config.get("configName").getAsString());
            
            dungeon = DungeonFactory.createDungeon(
                name, currMaxDungeonId, gameFile, true, loadedConfig);

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        return dungeon.createDungeonResponse();
    }

    /**
     * /game/dungeonResponseModel
     */
    public DungeonResponse getDungeonResponseModel() {

        return dungeon.createDungeonResponse();
    }

    /**
     * /game/tick/item
     */
    public DungeonResponse tick(String itemUsedId) throws IllegalArgumentException, InvalidActionException {
        int itemId = Integer.parseInt(itemUsedId);

        if (!dungeon.itemInPlayerInventory(itemId)) {
            dungeon.tick(false);
            throw new InvalidActionException("item not in player inventory");
        }
        if (!dungeon.itemIsUsable(itemId)) {
            dungeon.tick(false);
            throw new IllegalArgumentException("item not usable");
        }
        dungeon.useItem(itemId);
        dungeon.tick(false);
        return dungeon.createDungeonResponse();
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        dungeon.updateMovement(movementDirection);
        dungeon.tick(true);
        return dungeon.createDungeonResponse();
    }

    /**
     * /game/build
     */
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        // If buildable is not one of bow, shield
        if (!Dungeon.itemIsBuildable(buildable))
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
        int id = Integer.parseInt(entityId);
        if (!dungeon.itemIsInteractable(id))
            throw new IllegalArgumentException(entityId + " is not a valid Entity Id.");
        String interactAttempt = dungeon.interact(id);
        if (!interactAttempt.equals(ErrorString.SUCCESS.toString()))
            throw new InvalidActionException(interactAttempt);
        dungeon.tick(false);
        return dungeon.createDungeonResponse();
    }

    /**
     * /games/all
     */
    public List<String> allGames() {
        return saves();
    }

}
