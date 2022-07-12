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
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DungeonManiaController {

    private int currMaxDungeonId = 0;

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
        if (!dungeons().contains(dungeonName) || !configs().contains(configName)) {
            throw new IllegalArgumentException();
        }
        try {
            String jsonDungeon = new String(Files.readAllBytes(Paths.get("src/main/resources/dungeons/" + dungeonName + ".json")));
            JsonObject jsonObject = JsonParser.parseString(jsonDungeon).getAsJsonObject();
            JsonArray entitiesArray = jsonObject.get("entities").getAsJsonArray();
            JsonObject goals = jsonObject.get("goal-condition").getAsJsonObject();
            EntityController entityController = new EntityController();
            String jsonConfig = new String(Files.readAllBytes(Paths.get("src/main/resources/configs/" + configName + ".json")));
            JsonObject configs = JsonParser.parseString(jsonConfig).getAsJsonObject();
            entityController.startGame(entitiesArray, goals, configs, currMaxDungeonId + 1, dungeonName);
            currMaxDungeonId += 1;
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        return null;
    }

    /**
     * /game/dungeonResponseModel
     */
    public DungeonResponse getDungeonResponseModel() {
        return null;
    }

    /**
     * /game/tick/item
     */
    public DungeonResponse tick(String itemUsedId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        return null;
    }

    /**
     * /game/build
     */
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }
    
}
