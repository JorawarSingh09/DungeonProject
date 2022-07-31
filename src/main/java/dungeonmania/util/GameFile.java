package dungeonmania.util;

import com.google.gson.JsonArray;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.Entity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ThaiBuddhistDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GameFile {
    /**
     * get current time in thaiBuddhistDate
     * 
     * @return
     */
    private String getTime() {
        try {

            String tbd = ThaiBuddhistDate.now()
                    .getChronology().localDateTime(LocalDateTime.now())
                    .toString().replace(
                            "ThaiBuddhist ", "");

            // BE 2565-07-20T01:16:20.547005 <= 28

            return tbd;

        } catch (DateTimeException e) {
            System.out.println(
                    "passed parameter can"
                            + " not form a date");
            System.out.println(
                    "Exception thrown: " + e);
        }
        return null;
    }

    private String setSaveName(String dungeonName) {
        return dungeonName + java.time.LocalTime.now().toString();

    }

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
        // LocalTime time = LocalTime.now();
        try {
            Writer writer = new FileWriter(FileLoader.createSaveFolder()
                    + dungeon.getDungeonId() + ".json");

            gson.toJson(map, writer);
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
