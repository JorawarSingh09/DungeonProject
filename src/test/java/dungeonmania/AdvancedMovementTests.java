package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class AdvancedMovementTests {

    @Test
    @DisplayName("Test mercenary moves towards the player")
    public void mercMovesTowardsPlayer() {
        DungeonManiaController dmc = TestUtils.createDungeon("adv1", "bribe_amount_3");
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(0, -1),
                TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition());
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(1, -1),
                TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition());
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(2, -1),
                TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition());
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(3, -1),
                TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition());
    }

    @Test
    @DisplayName("Test mercenary accounts for Walls")
    public void mercGoesAroundWalls() {
        DungeonManiaController dmc = TestUtils.createDungeon("advanced", "bribe_amount_3");
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(2, 5),
                TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition());
    }

    @Test
    @DisplayName("Test mercenary accounting for swamp tiles 1")
    public void mercAccountsForSwampTilesSimple() {
        DungeonManiaController dmc = TestUtils.createDungeon("adv2", "bribe_amount_3");
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(-1, -2),
                TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition());
    }

    @Test
    @DisplayName("Test mercenary accounting for swamp tiles 2")
    public void mercAccountsForSwampTilesComplex() {
        DungeonManiaController dmc = TestUtils.createDungeon("adv3", "bribe_amount_3");
        dmc.tick(Direction.RIGHT);
        assertTrue((new Position(-2, 0))
                .equals(TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition()) ||
                (new Position(-2, -2)).equals(
                        TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition()));
    }

    @Test
    @DisplayName("Test mercenary accounting for portals 1")
    public void mercAccountsForPortals() {
        DungeonManiaController dmc = TestUtils.createDungeon("adv4", "bribe_amount_3");
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(0, -2),
                TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition());
    }

    @Test
    @DisplayName("Test mercenary accounting for portals 2")
    public void mercAccountsForPortalsAdv() {
        DungeonManiaController dmc = TestUtils.createDungeon("adv5", "bribe_amount_3");
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(0, -2),
                TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition());
    }

    @Test
    @DisplayName("Test assassin moves towards the player")
    public void mercAssassinTowardsPlayer1() {
        DungeonManiaController dmc = TestUtils.createDungeon("adv6", "bribe_amount_3");
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(0, -1),
                TestUtils.getEntities(dmc.getDungeonResponseModel(), "assassin").get(0).getPosition());
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(1, -1),
                TestUtils.getEntities(dmc.getDungeonResponseModel(), "assassin").get(0).getPosition());
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(2, -1),
                TestUtils.getEntities(dmc.getDungeonResponseModel(), "assassin").get(0).getPosition());
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(3, -1),
                TestUtils.getEntities(dmc.getDungeonResponseModel(), "assassin").get(0).getPosition());
    }

    @Test
    @DisplayName("Mercenary is an ally and the player doesn't move")
    public void mercAssassinTowardsPlayer2() {
        DungeonManiaController dmc = TestUtils.createDungeon("adv7", "briber");
        dmc.tick(Direction.LEFT);
        assertDoesNotThrow(() -> dmc.interact(TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getId()));
        assertEquals(new Position(0, 0), TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition());
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(-1, 0), TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition());
    }

    @Test
    @DisplayName("Mercenary is completely out of range")
    public void mercAssassinTowardsPlayer3() {        DungeonManiaController dmc = TestUtils.createDungeon("adv", "briber");

        dmc.tick(Direction.DOWN);
        assertEquals(new Position(1, -99), TestUtils.getEntities(dmc.getDungeonResponseModel(), "mercenary").get(0).getPosition());

    }

    @Test
    @DisplayName("Assassin is completely out of range")
    public void mercAssassinTowardsPlayer4() {
        DungeonManiaController dmc = TestUtils.createDungeon("adv8", "briber");
        dmc.tick(Direction.DOWN);
        assertEquals(new Position(1, -99), TestUtils.getEntities(dmc.getDungeonResponseModel(), "assassin").get(0).getPosition());
    } 

    @Test
    @DisplayName("Test assassin accounting for portals 1")
    public void AssAccountsForPortals() {
        DungeonManiaController dmc = TestUtils.createDungeon("adv9", "bribe_amount_3");
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(0, -2),
                TestUtils.getEntities(dmc.getDungeonResponseModel(), "assassin").get(0).getPosition());
    }

    @Test
    @DisplayName("Test assassin accounting for portals 2")
    public void AssAccountsForPortalsAdv() {
        DungeonManiaController dmc = TestUtils.createDungeon("adv10", "bribe_amount_3");
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        assertEquals(new Position(0, -2), TestUtils.getEntities(dmc.getDungeonResponseModel(), "assassin").get(0).getPosition());
    }
}
