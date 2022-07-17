package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.EntityResolver;

import dungeonmania.entities.Entity;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;

import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.getInventory;;

public class ArrowsTests {
    /**
     * Can be picked up by the player.
     */

     @Test
     @DisplayName("Test that an arrow spawns")
     public void testArrowSpawn(){
        DungeonManiaController dmc  = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
            "d_simpleArrow", "bribe_radius_1");

        assertEquals(1, getEntities(res, "arrow").size());
     }

     @Test
     @DisplayName("Test that multiple arrows can spawn")
     public void testArrowSpawnMultiple(){
        DungeonManiaController dmc  = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
            "d_simpleArrows", "bribe_radius_1");

        assertEquals(2, getEntities(res, "arrow").size());
     }

     @Test
     @DisplayName("Test that an arrow can be picked up")
     public void testArrowPickup(){
        DungeonManiaController dmc  = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
            "d_simpleArrow", "bribe_radius_1");

        assertEquals(0, getInventory(res, "arrow").size());

        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, getInventory(res, "arrow").size());
        assertEquals(0, getEntities(res, "arrow").size());
     }

     @Test
     @DisplayName("Test that an multiple arrows can be picked up")
     public void testMultipleArrowPickup(){
        DungeonManiaController dmc  = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
            "d_simpleArrows", "bribe_radius_1");

        assertEquals(0, getInventory(res, "arrow").size());

        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, getInventory(res, "arrow").size());
        assertEquals(1, getEntities(res, "arrow").size());

        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, getInventory(res, "arrow").size());
        assertEquals(0, getEntities(res, "arrow").size());
     }
}
