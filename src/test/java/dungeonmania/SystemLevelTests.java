package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.util.Direction;

import static dungeonmania.TestUtils.getEntities;

public class SystemLevelTests {

    @Test
    @DisplayName("Player collects invincibility potion, invisibility potion, sword, treasure, bribe merc, opens doors")
    public void playerAdv() {
        DungeonManiaController dmc = TestUtils.createDungeon("advanced", "bribe_radius_1");
        // Pick up the invincibility potion.
        dmc.tick(Direction.RIGHT);
        assertEquals(1, dmc.getDungeonResponseModel().getInventory().size());
        // Pick up the invisibility potion.
        dmc.tick(Direction.RIGHT);
        assertEquals(2, dmc.getDungeonResponseModel().getInventory().size());
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        assertEquals(2, dmc.getDungeonResponseModel().getInventory().size());
        // Pick up the sword.
        dmc.tick(Direction.RIGHT);
        assertEquals(3, dmc.getDungeonResponseModel().getInventory().size());
        dmc.tick(Direction.LEFT);
        assertEquals(2, dmc.getDungeonResponseModel().getInventory().size());
        assertEquals(1, dmc.getDungeonResponseModel().getBattles().size());
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        // Pick up treasure
        assertEquals(3, dmc.getDungeonResponseModel().getInventory().size());
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        assertEquals(4, dmc.getDungeonResponseModel().getInventory().size());
        // Can only hold one key at a time
        // dmc.tick(Direction.RIGHT);
        // assertEquals(4, dmc.getDungeonResponseModel().getInventory().size());
        // dmc.tick(Direction.LEFT);
        dmc.tick(Direction.DOWN);
        assertEquals(5, dmc.getDungeonResponseModel().getInventory().size());
        dmc.tick(Direction.DOWN);
        Assertions.assertDoesNotThrow(() -> (dmc.build("shield")));
        assertEquals(4, dmc.getDungeonResponseModel().getInventory().size());
        dmc.tick(Direction.DOWN);
        assertEquals(5, dmc.getDungeonResponseModel().getInventory().size());
        dmc.tick(Direction.DOWN);
        assertEquals(6, dmc.getDungeonResponseModel().getInventory().size());
        dmc.tick(Direction.RIGHT);
        assertEquals(7, dmc.getDungeonResponseModel().getInventory().size());
        dmc.tick(Direction.RIGHT);
        assertEquals(8, dmc.getDungeonResponseModel().getInventory().size());
        Assertions.assertDoesNotThrow(() -> (dmc.build("bow")));
        assertEquals(5, dmc.getDungeonResponseModel().getInventory().size());
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.UP);
        dmc.tick(Direction.UP);
        dmc.tick(Direction.UP);
        dmc.tick(Direction.UP);
        dmc.tick(Direction.UP);
        assertEquals(4, dmc.getDungeonResponseModel().getInventory().size());
    }

    @Test
    @DisplayName("Player pushes boulders to complete goal")
    public void playerPushBoulderCompleteGoal() {
        DungeonManiaController dmc = TestUtils.createDungeon("boulders", "bribe_radius_1");
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.UP);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.DOWN);
        assertTrue(dmc.getDungeonResponseModel().getGoals().contains(":boulders"));
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.UP);
        dmc.tick(Direction.UP);
        assertTrue(dmc.getDungeonResponseModel().getGoals().equals(""));
    }

    @Test
    @DisplayName("Player destroys zombiespawner")
    public void playerDestroysZombieSpawner() {
        DungeonManiaController dmc = TestUtils.createDungeon("zombies", "increasedSwordDurbility");
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        Assertions.assertDoesNotThrow(() -> (dmc.interact("2")));
        assertEquals(0, getEntities(dmc.getDungeonResponseModel(), "zombie_toast_spawner").size());
    }

}
