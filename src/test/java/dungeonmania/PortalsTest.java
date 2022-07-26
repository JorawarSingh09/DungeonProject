package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import static dungeonmania.TestUtils.getEntities;

public class PortalsTest {

    @Test
    @DisplayName("test if player goes through portal correctly")
    public void moveUpPortal() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("portals1", "bribe_amount_3");

        dmc.tick(Direction.UP);
        // assert player position is same
        assertEquals(dmc.dungeon.getPlayer().getPreviousPosition(), dmc.dungeon.getPlayer().getPosition());

    }

    @Test
    @DisplayName("Check if boulder does through portal")
    public void boulderGoesThrough() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("portals2", "bribe_amount_3");

        Position boulderPos = getEntities(res, "boulder").get(0).getPosition();
        Position playerPos = getEntities(res, "player").get(0).getPosition();

        assertEquals(new Position(1, 3), boulderPos);
        assertEquals(new Position(1, 4), playerPos);

        res = dmc.tick(Direction.UP);
        boulderPos = getEntities(res, "boulder").get(0).getPosition();
        playerPos = getEntities(res, "player").get(0).getPosition();

        assertEquals(new Position(1, -1), boulderPos);
        assertEquals(new Position(1, 3), playerPos);

    }

    @Test
    @DisplayName("Check if mercenary does through portal")
    public void mercGoesThrough() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("portals3", "bribe_amount_3");

        Position mercPos = getEntities(res, "mercenary").get(0).getPosition();
        Position playerPos = getEntities(res, "player").get(0).getPosition();

        assertEquals(new Position(1, 4), mercPos);
        assertEquals(new Position(1, 3), playerPos);

        res = dmc.tick(Direction.UP);
        mercPos = getEntities(res, "mercenary").get(0).getPosition();
        playerPos = getEntities(res, "player").get(0).getPosition();

        assertEquals(new Position(1, -1), playerPos);
        assertEquals(new Position(1, 3), mercPos);

        res = dmc.tick(Direction.UP);
        mercPos = getEntities(res, "mercenary").get(0).getPosition();
        playerPos = getEntities(res, "player").get(0).getPosition();

        assertEquals(new Position(1, -2), playerPos);
        assertEquals(new Position(1, -1), mercPos);

    }

    @Test
    @DisplayName("Check if player goes through portal, when one block is occupied")
    public void playerGoesThroughwhenBlocked() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("portals4", "bribe_amount_3");

        Position oldPlayerPos = getEntities(res, "player").get(0).getPosition();
        Position wallPos = getEntities(res, "wall").get(0).getPosition();
        assertEquals(new Position(1, 3), oldPlayerPos);

        res = dmc.tick(Direction.UP);
        Position newPlayerPos = getEntities(res, "player").get(0).getPosition();

        assertNotEquals(wallPos, newPlayerPos);
        assertNotEquals(oldPlayerPos, newPlayerPos);

    }

    @Test
    @DisplayName("Check if player does not go through portal, all sides of portal are blocked")
    public void portalBlocked() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("portals5", "bribe_amount_3");

        Position oldPlayerPos = getEntities(res, "player").get(0).getPosition();
        assertEquals(new Position(1, 3), oldPlayerPos);

        res = dmc.tick(Direction.UP);
        Position newPlayerPos = getEntities(res, "player").get(0).getPosition();

        assertEquals(oldPlayerPos, newPlayerPos);

    }

}
