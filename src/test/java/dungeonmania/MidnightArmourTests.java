package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.getInventory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class MidnightArmourTests {
    @Test
    @DisplayName("Test that player cannot build armour with insufficient materials")
    public void armourInsufficientMaterials() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_MidnightArmourTest", "c_MidnightArmourTest");
        assertThrows(InvalidActionException.class, () -> {
            dmc.build("midnight_armour");
        });
    }

    @Test
    @DisplayName("Test that player cannot build armour with zombie existent")
    public void armourHasZombie() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_MidnightArmourTest", "c_MidnightArmourTest");
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        DungeonResponse res = dmc.tick(Direction.LEFT);
        if (getEntities(res, "zombie_toast").size() == 1){
            assertThrows(InvalidActionException.class, () -> {
                dmc.build("midnight_armour");
            });
        }
    }

    @Test
    @DisplayName("Test that player can build armour with different moving entities")
    public void armourBuild() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_MidnightArmourTest", "c_MidnightArmourTest");
        dmc.tick(Direction.RIGHT);
        DungeonResponse res = dmc.tick(Direction.RIGHT);
        assertEquals(1, getEntities(res, "spider").size());
        res = assertDoesNotThrow(() -> dmc.build("midnight_armour"));
        assertEquals(1, getInventory(res, "midnight_armour").size());
    }

}
