package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Direction;

public class SunstoneTests {
    
    @Test
    @DisplayName("test adds to the treasure goal")
    public void sunstoneTreasureGoal() {
        DungeonManiaController dmc = TestUtils.createDungeon("sunstone", "bribe_amount_3");
        // Move right to collect sunstone
        dmc.tick(Direction.RIGHT);
        // Got a sunstone
        assertEquals(1, dmc.getDungeonResponseModel().getInventory().size());
        // Completed the treasure goal by using a sunstone
        assertEquals("", TestUtils.getGoals(dmc.getDungeonResponseModel()));
    }

    @Test
    @DisplayName("test unlocks doors and does not lose sunstone")
    public void sunstoneUnlockDoor() {
        DungeonManiaController dmc = TestUtils.createDungeon("sunstone", "bribe_amount_3");
        // Move right to collect sunstone
        dmc.tick(Direction.RIGHT);
        assertEquals(1, dmc.getDungeonResponseModel().getInventory().size());
        // Check that we can move onto door with sunstone
        dmc.tick(Direction.RIGHT);
        assertEquals(3, TestUtils.getEntities(dmc.getDungeonResponseModel(), "player").get(0).getPosition().getX());
        // Check can move through the door and get the key but not lose the sunstone
        dmc.tick(Direction.RIGHT);
        assertEquals(4, TestUtils.getEntities(dmc.getDungeonResponseModel(), "player").get(0).getPosition().getX());
        assertEquals(2, dmc.getDungeonResponseModel().getInventory().size());
    }

    @Test
    @DisplayName("test unlocks doors but does not lose key")
    public void sunstoneUnlockDoorNotLoseKey() {
        DungeonManiaController dmc = TestUtils.createDungeon("sunstone", "bribe_amount_3");
        // Move right to pick up sunstone
        dmc.tick(Direction.RIGHT);
        assertEquals(1, dmc.getDungeonResponseModel().getInventory().size());
        // Move to get the key
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.UP);
        // Got key
        assertEquals(2, dmc.getDungeonResponseModel().getInventory().size());
        // Attempt to go through door and does not lose key or sunstone
        dmc.tick(Direction.LEFT);
        assertEquals(3, TestUtils.getEntities(dmc.getDungeonResponseModel(), "player").get(0).getPosition().getX());
        assertEquals(2, dmc.getDungeonResponseModel().getInventory().size());
    }

    // @Test
    // @DisplayName("test lost sunstone when build a sceptre")
    // public void sunstoneDepleteBuildSceptre() {
    //     DungeonManiaController dmc = TestUtils.createDungeon("sunstone", "c_battleTests_basicMercenaryMercenaryDies");
    //     // Pick up sunstone
    //     dmc.tick(Direction.RIGHT);
    //     assertEquals(1, dmc.getDungeonResponseModel().getInventory().size());
    //     dmc.tick(Direction.LEFT);
    //     // Pick up wood
    //     dmc.tick(Direction.LEFT);
    //     assertEquals(2, dmc.getDungeonResponseModel().getInventory().size());
    //     // Pick up treasure
    //     dmc.tick(Direction.LEFT);
    //     assertEquals(3, dmc.getDungeonResponseModel().getInventory().size());
    //     // Build sceptre and lost everything
    //     Assertions.assertDoesNotThrow(() -> (dmc.build("sceptre")));
    //     assertEquals(1, dmc.getDungeonResponseModel().getInventory().size());
    // }

    @Test
    @DisplayName("test lost sunstone when build a Midnight armour")
    public void sunstoneDepleteBuildMidnightArmour() {
        DungeonManiaController dmc = TestUtils.createDungeon("sunstone", "c_battleTests_basicMercenaryMercenaryDies");
        // Pick up sunstone
        dmc.tick(Direction.RIGHT);
        assertEquals(1, dmc.getDungeonResponseModel().getInventory().size());
        dmc.tick(Direction.LEFT);
        // Pick up wood
        dmc.tick(Direction.LEFT);
        assertEquals(2, dmc.getDungeonResponseModel().getInventory().size());
        // Pick up treasure
        dmc.tick(Direction.LEFT);
        assertEquals(3, dmc.getDungeonResponseModel().getInventory().size());
        // Pick up sword
        dmc.tick(Direction.LEFT);
        assertEquals(4, dmc.getDungeonResponseModel().getInventory().size());
        // Build sceptre and lost everything
        Assertions.assertDoesNotThrow(() -> (dmc.build("midnight_armour")));
        assertEquals(3, dmc.getDungeonResponseModel().getInventory().size());
    }

    @Test
    @DisplayName("test kept sunstone when build a shield")
    public void sunstoneDepleteBuildMShield() {
        DungeonManiaController dmc = TestUtils.createDungeon("sunstone", "c_battleTests_basicMercenaryMercenaryDies");
        // Pick up sun stone
        dmc.tick(Direction.RIGHT);
        assertEquals(1, dmc.getDungeonResponseModel().getInventory().size());
        // Pick up wood
        dmc.tick(Direction.UP);
        assertEquals(2, dmc.getDungeonResponseModel().getInventory().size());
        // Pick up more wood
        dmc.tick(Direction.UP);
        assertEquals(3, dmc.getDungeonResponseModel().getInventory().size());
        // Test made shield and kept the sunstone
        Assertions.assertDoesNotThrow(() -> (dmc.build("shield")));
        System.out.println(dmc.getDungeonResponseModel().getInventory().size());
        assertEquals(2, dmc.getDungeonResponseModel().getInventory().size());
    }

    @Test
    @DisplayName("test use sunstone instead even when have treasure when build a shield")
    public void sunstoneDepleteHaveTreasureBuildMShield() {
        DungeonManiaController dmc = TestUtils.createDungeon("sunstone", "c_battleTests_basicMercenaryMercenaryDies");
        // Pick up treasure
        dmc.tick(Direction.LEFT);
        assertEquals(1, dmc.getDungeonResponseModel().getInventory().size());
        // Pick up sun stone
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        assertEquals(2, dmc.getDungeonResponseModel().getInventory().size());
        // Pick up wood
        dmc.tick(Direction.UP);
        assertEquals(3, dmc.getDungeonResponseModel().getInventory().size());
        // Pick up more wood
        dmc.tick(Direction.UP);
        assertEquals(4, dmc.getDungeonResponseModel().getInventory().size());
        // Test made shield and kept the sunstone and treasure
        Assertions.assertDoesNotThrow(() -> (dmc.build("shield")));
        assertEquals(3, dmc.getDungeonResponseModel().getInventory().size());
    }

    @Test
    @DisplayName("test that does not work for bribe")
    public void sunstoneDoesNotBribe() {
        DungeonManiaController dmc = TestUtils.createDungeon("sunstone", "bribe_amount_3");
        // Pick up sun stone
        dmc.tick(Direction.RIGHT);
        assertEquals(1, dmc.getDungeonResponseModel().getInventory().size());
        // Check that the sunstone cannot be used for a bribe
        String mercId = dmc.getDungeonResponseModel().getEntities().stream().filter(t -> t.getType().equals("mercenary")).collect(Collectors.toList()).get(0).getId();
        Assertions.assertThrows(InvalidActionException.class, () -> {
            dmc.interact(mercId);
        });
    }

}
