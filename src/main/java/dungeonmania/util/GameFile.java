package dungeonmania.util;

import com.google.gson.JsonArray;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GameFile {

    public static void saveDungeon(Dungeon dungeon) throws URISyntaxException {
        JsonArray entities = new JsonArray();
        Map<String, Object> map = new HashMap<>();
        List<Entity> entitiesOnMap = dungeon.getEntities();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        map.put("maxEntityID", dungeon.getCurrMaxEntityId());
        map.put("config", dungeon.getConfigJson());
        entitiesOnMap.forEach(e -> entities.add(e.getJson()));
        map.put("entities", entities);
        map.put("goal-condition", dungeon.getGoal().getJson(dungeon));
        try {
            Writer writer = new FileWriter(FileLoader.createSaveFolder()
                    + dungeon.getDungeonId() + dungeon.getDungeonName() + ".json");

            gson.toJson(map, writer);
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
