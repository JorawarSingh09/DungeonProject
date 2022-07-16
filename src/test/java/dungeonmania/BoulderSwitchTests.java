package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class BoulderSwitchTests {
    @Test
    @DisplayName("Test the player can move towards floor switch")
    public void testTowardsFloorSwitch() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_BoulderSwitchTest",
                "c_movementTest_testMovementDown");

        // move player left
        DungeonResponse res = dmc.tick(Direction.LEFT);
        EntityResponse actualPlayer = getPlayer(res).get();

        Position playerPos = actualPlayer.getPosition();
        Position switchPos = getEntities(res, "switch").get(1).getPosition();

        assertEquals(playerPos, switchPos);
    }

    @Test
    @DisplayName("Test the player can move towards the boulder")
    public void testTowardsBoulder() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_BoulderSwitchTest",
                "c_movementTest_testMovementDown");

        Position boulderPos = getEntities(res, "boulder").get(0).getPosition();

        dmc.tick(Direction.UP);
        dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        Position playerPos = getPlayer(res).get().getPosition();
        assertEquals(boulderPos, playerPos);

        Position expectedPos = new Position(2, 2);
        boulderPos = getEntities(res, "boulder").get(0).getPosition();
        assertEquals(boulderPos, expectedPos);
    }

    @Test
    @DisplayName("Test that player can not move towards boulder")
    public void testTowardsBoulderBlocked() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_BoulderSwitchTest", "c_movementTest_testMovementDown");
        res = dmc.tick(Direction.RIGHT);
        Position boulderPos = getEntities(res, "boulder").get(0).getPosition();
        Position playerPos = getPlayer(res).get().getPosition();
        assertEquals(boulderPos, new Position(2, 1));
        assertEquals(playerPos, new Position(1, 1));
    }
}
