package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.util.Direction;

public class InvisibilityTests {
    @Test
    @DisplayName("Player drinks invisibility potion and avoids a fight")
    public void playerDrinkInvis() {
        DungeonManiaController dmc = TestUtils.createDungeon("playerState", "playerState");
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.UP);
        assertDoesNotThrow(() -> (dmc.tick("2")));
        dmc.tick(Direction.RIGHT);
        assertEquals(0, dmc.getDungeonResponseModel().getBattles().size());
        dmc.tick(Direction.RIGHT);
        assertEquals(0, dmc.getDungeonResponseModel().getBattles().size());
    }
}
