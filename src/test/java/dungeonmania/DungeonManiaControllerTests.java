package dungeonmania;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Direction;

public class DungeonManiaControllerTests {

    @Test
    @DisplayName("Dungeon name does not exist")
    public void noSuchDungeon() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TestUtils.createDungeon("notvaliddungeon", "battleTest_config");
        });
    }

    @Test
    @DisplayName("Config name does not exist")
    public void noSuchConfig() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TestUtils.createDungeon("battle_test", "notvalidconfig");
        });
    }

    @Test
    @DisplayName("Dungeon and config name do not exist")
    public void noSuchDungeonConfig() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TestUtils.createDungeon("notvaliddungeon", "notvalidconfig");
        });
    }

    @Test
    @DisplayName("Check useItem throws exception if item is not usable")
    public void useItemNotUsable() {
        DungeonManiaController dmc = TestUtils.createDungeon("battle_test", "battleTest_config");
        dmc.tick(Direction.LEFT);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            dmc.tick("5");
        });
    }

    @Test
    @DisplayName("Throws exception if item is usable but not in inventory")
    public void itemNotInInventory() {
        DungeonManiaController dmc = TestUtils.createDungeon("battle_test", "battleTest_config");
        dmc.tick(Direction.LEFT);
        Assertions.assertThrows(InvalidActionException.class, () -> {
            dmc.tick("8");
        });
    }

    @Test
    @DisplayName("If buildable is not one of bow, shield")
    public void notBuildable() {
        DungeonManiaController dmc = TestUtils.createDungeon("battle_test", "battleTest_config");
        dmc.tick(Direction.LEFT);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            dmc.build("notBuildable");
        });
    }

    @Test
    @DisplayName("Player does not have sufficient items to build")
    public void notEnoughItemsToBuild() {
        DungeonManiaController dmc = TestUtils.createDungeon("battle_test", "battleTest_config");
        dmc.tick(Direction.LEFT);
        Assertions.assertThrows(InvalidActionException.class, () -> {
            dmc.build("bow");
        });
    }

    @Test
    @DisplayName("Player is not within specified bribing radius to the mercenary")
    public void notInBribeRadius() {
        DungeonManiaController dmc = TestUtils.createDungeon("battle_test", "battleTest_config");
        dmc.tick(Direction.LEFT);
        Assertions.assertThrows(InvalidActionException.class, () -> {
            dmc.interact("3");
        });
    }

    @Test
    @DisplayName("EntityId is not a valid entity ID")
    public void entityIdDoesNotExist() {
        DungeonManiaController dmc = TestUtils.createDungeon("battle_test", "battleTest_config");
        dmc.tick(Direction.LEFT);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            dmc.interact("-1");
        });
    }

    @Test
    @DisplayName("Player is not cardinally adjacent to the spawner")
    public void notAdjacentToSpawner() {
        DungeonManiaController dmc = TestUtils.createDungeon("mania", "battleTest_config");
        for (int i = 0; i < 5; i++) {
            dmc.tick(Direction.RIGHT);
        }
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        Assertions.assertThrows(InvalidActionException.class, () -> {
            dmc.interact("1");
        });
    }

    @Test
    @DisplayName("Player does not have enough gold and attempts to bribe")
    public void notEnoughGoldToBribe() {
        DungeonManiaController dmc = TestUtils.createDungeon("merc", "battleTest_config");
        dmc.tick(Direction.RIGHT);
        Assertions.assertThrows(InvalidActionException.class, () -> {
            dmc.interact("1");
        });
    }

    @Test
    @DisplayName("player does not have a weapon and attempts to destroy a spawner")
    public void noWeaponToDestroy() {
        DungeonManiaController dmc = TestUtils.createDungeon("mania", "battleTest_config");
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        Assertions.assertThrows(InvalidActionException.class, () -> {
            dmc.interact("1");
        });
    }

}
