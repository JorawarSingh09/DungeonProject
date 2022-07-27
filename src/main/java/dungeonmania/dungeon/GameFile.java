package dungeonmania.dungeon;

import com.google.gson.JsonArray;
import dungeonmania.entities.Entity;
import dungeonmania.util.FileLoader;

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
    private static String getTime() {
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

    private static String setSaveName(String dungeonName) {
        return dungeonName + java.time.LocalTime.now().toString();

    }

    public static void saveDungeon(Dungeon dungeon) {
        JsonArray entities = new JsonArray();
        Map<String, Object> map = new HashMap<>();
        List<Entity> entitiesOnMap = dungeon.getEntities();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        entitiesOnMap.forEach(e -> entities.add(e.getJson()));
        map.put("entities", entities);
        // get json stuff in dungeon??
        map.put("goal-condition", dungeon.getGoal().getJson(dungeon));
        try {
            Writer writer = new FileWriter("src/main/resources/saves/" + 
            dungeon.getDungeonName() + ".json");

            String json2 = new String(
                    FileLoader.loadResourceFile("configs/" + dungeon.getConfigName() +".json"));
            Map secondObject = new Gson().fromJson(json2, HashMap.class);
            map.putAll(secondObject);

            gson.toJson(map, writer);
            writer.close();
             
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
