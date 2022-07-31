package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;

public class PersistenceTests {

    @Test
    @DisplayName("test that player location is saved and loaded")
    public void basicPersistence() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("EverythingDungeon", "bribe_amount_3");
        Map<String, Object> newMap = dmc.dungeon.getJsonMap();
        dmc.dungeon.getDungeonId();
        dmc.saveGame(dmc.dungeon.getDungeonId());

        dmc = new DungeonManiaController();

        System.out.println(DungeonManiaController.saves());
        dmc.loadGame(DungeonManiaController.saves().get(0));
        assertEquals(newMap, dmc.dungeon.getJsonMap());
    }

    @Test
    @DisplayName("test that player inventory is saved and loaded")
    public void inventoryPersistence() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("EverythingDungeon", "bribe_amount_3");
        for (int i = 0; i < 9; i++) {
            dmc.tick(Direction.LEFT);
        }
        // Map<String, Object> newMap= dmc.dungeon.getJsonMap();
        dmc.dungeon.getDungeonId();
        dmc.saveGame(dmc.dungeon.getDungeonId());
        List<ItemResponse> savedItems = dmc.dungeon.createItemResponse();

        dmc = new DungeonManiaController();

        System.out.println(DungeonManiaController.saves());
        dmc.loadGame(DungeonManiaController.saves().get(0));
        assertEquals(savedItems.size(), dmc.dungeon.createItemResponse().size());
        // assertEquals(newMap, dmc.dungeon.getJsonMap());
    }
}
