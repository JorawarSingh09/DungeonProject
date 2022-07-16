package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;

public class InvincibilityTests {
    
    @Test
    @DisplayName("Player drinks invincibility potion and fights to win round 1")
    public void aliveToInvisState() {
        DungeonManiaController dmc = TestUtils.createDungeon("invince", "playerState");
        dmc.tick(Direction.RIGHT);
        assertDoesNotThrow(()->(dmc.tick("1")));
        dmc.tick(Direction.RIGHT);
        assertEquals(1, dmc.getDungeonResponseModel().getBattles().size());
    }
}
