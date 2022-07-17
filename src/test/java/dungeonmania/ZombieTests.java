package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static dungeonmania.TestUtils.getPlayer;
import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.getInventory;
import static dungeonmania.TestUtils.getGoals;
import static dungeonmania.TestUtils.countEntityOfType;
import static dungeonmania.TestUtils.getValueFromConfigFile;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieTests {
    @Test
    @DisplayName("Test that player cannot move towards zombietoastspawner")
    public void testZombieToastSpawnerStatic() {
        DungeonManiaController dmc = TestUtils.createDungeon("d_zombiesTest", "c_zombiesTest");
        dmc.tick(Direction.RIGHT);
        DungeonResponse res = dmc.tick(Direction.DOWN);
        Position playerPos = getPlayer(res).get().getPosition();
        Position spawnerPos = getEntities(res, "zombie_toast_spawner").get(0).getPosition();
        assertEquals(playerPos, new Position(2, 1));
        assertEquals(spawnerPos, new Position(2, 2));
    }

    @Test
    @DisplayName("Test zombie movement when only one position available and kill zombie")
    public void testZombie() {
        DungeonManiaController dmc = TestUtils.createDungeon("d_zombiesTest2", "c_zombiesTest");
        DungeonResponse res = dmc.tick(Direction.DOWN);
        Position zombiePos = getEntities(res, "zombie_toast").get(0).getPosition();
        assertEquals(new Position(1, 3), zombiePos);
        res = dmc.tick(Direction.DOWN);
        assertEquals(0, getEntities(res, "zombie_toast").size());
    }

    @Test
    @DisplayName("Test that the right number of zombies spawn")
    public void testZombieSpawnNumber() {
        DungeonManiaController dmc = TestUtils.createDungeon("d_zombiesTest", "c_zombiesTest");
        DungeonResponse res = dmc.getDungeonResponseModel();
        int size = getEntities(res, "zombie_toast").size();
        assertEquals(0, size);
        res = dmc.tick(Direction.UP);
        size = getEntities(res, "zombie_toast").size();
        assertEquals(0, size);
        res = dmc.tick(Direction.UP);
        size = getEntities(res, "zombie_toast").size();
        assertEquals(1, size);
        assertEquals(new Position(2, 1), getEntities(res, "zombie_toast").get(0).getPosition());
    }

    @Test
    @DisplayName("Test that player cannot interact with zombie spawner without weapon")
    public void testInteractZombieSpawnerNoWeapon() {
        DungeonManiaController dmc = TestUtils.createDungeon("d_zombiesTest", "c_zombiesTest");
        DungeonResponse res = dmc.getDungeonResponseModel();
        dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        String id = getEntities(res, "zombie_toast_spawner").get(0).getId();
        assertThrows(InvalidActionException.class, () -> dmc.interact(id));
    }

    @Test
    @DisplayName("Test that player cannot interact with zombie spawner due to not adjacent")
    public void testInteractZombieSpawnerNotAdjacent() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_zombiesTest", "c_zombiesTest");
        res = dmc.tick(Direction.RIGHT);
        String id = getEntities(res, "zombie_toast_spawner").get(0).getId();
        assertThrows(InvalidActionException.class, () -> dmc.interact(id));
    }

    @Test
    @DisplayName("Test that player can interact with zombie spawner")
    public void testInteractZombieSpawnerSuccess() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_zombiesTest", "c_zombiesTest");
        Position swordPos = getEntities(res, "sword").get(0).getPosition();
        assertEquals(new Position(3, 1), swordPos);
        dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        Position playerPos = getPlayer(res).get().getPosition();
        assertEquals(new Position(3, 1), playerPos);
        assertEquals(1, getInventory(res, "sword").size());
        res = dmc.tick(Direction.LEFT);
        String id = getEntities(res, "zombie_toast_spawner").get(0).getId();
        assertDoesNotThrow(() -> dmc.interact(id));
    }
}
