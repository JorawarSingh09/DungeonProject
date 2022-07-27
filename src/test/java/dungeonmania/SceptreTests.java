package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static dungeonmania.TestUtils.getPlayer;
import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.getInventory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SceptreTests {
    @Test
    @DisplayName("Test the player cannot build with insufficient materials")
    public void sceptreInsufficientMaterials() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_SceptreTest", "c_SceptreTest");

        assertThrows(InvalidActionException.class, () -> {
            dmc.build("sceptre");
        });
    }

    @Test
    @DisplayName("Test the player can build with sunstone, wood and key")
    public void sceptreBuild1() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_SceptreTest", "c_SceptreTest");
        dmc.tick(Direction.UP);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        DungeonResponse res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, getInventory(res, "sceptre").size());
    }

    @Test
    @DisplayName("Test the player can build with sunstone, arrows and treasure")
    public void sceptreBuild2() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_SceptreTest", "c_SceptreTest");
        dmc.tick(Direction.UP);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        DungeonResponse res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, getInventory(res, "sceptre").size());
    }

    @Test
    @DisplayName("Test the player can mind control mercenary")
    public void sceptreMindControlMerc() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_SceptreTest", "c_SceptreTest");
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        String id = getEntities(res, "mercenary").get(0).getId();
        assertEquals(1, getEntities(res, "mercenary").size());
        Position mercPos = getEntities(res, "mercenary").get(0).getPosition();
        assertEquals(new Position(-2, 0), mercPos);
        Position playerPos = getPlayer(res).get().getPosition();
        assertEquals(new Position(-1, 0), playerPos);
        assertDoesNotThrow(() -> dmc.interact(id));
        res = dmc.tick(Direction.UP);
        assertEquals(1, getEntities(res, "mercenary").size());
        res = dmc.tick(Direction.UP);
        assertEquals(1, getEntities(res, "mercenary").size());
        res = dmc.tick(Direction.UP);
        assertEquals(0, getEntities(res, "mercenary").size());
    }

    @Test
    @DisplayName("Test the player can mind control assassin")
    public void sceptreMindControlAss() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_SceptreTest", "c_SceptreTest");
        assertEquals(1, getEntities(res, "assassin").size());
        dmc.tick(Direction.UP);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        String id = getEntities(res, "assassin").get(0).getId();
        assertDoesNotThrow(() -> dmc.interact(id));
    }
}
