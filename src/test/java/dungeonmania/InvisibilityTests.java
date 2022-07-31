package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class InvisibilityTests {
    @Test
    @DisplayName("Player drinks invincibility potion and fights to win round 1")
    // should this not be an integration test
    public void invinceToAlive() throws InvalidActionException {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame(
                "invincibleTest", "playerState");
        Dungeon dungeon = dmc.dungeon;
        dungeon.getPlayer().addItem(new InvisibilityPotion(12,
                new Position(0, 0),
                2));

        String playerState = dungeon.getPlayer().getPlayerState().toString();
        Mercenary merc = (Mercenary) dungeon.getEntitiesOfType("mercenary").get(0);
        String mercState = merc.getMovementStrategy().toString();

        assertTrue(playerState.equals("Alive"));
        assertTrue(mercState.equals("Follow"));

        dmc.tick("12");
        playerState = dungeon.getPlayer().getPlayerState().toString();
        merc = (Mercenary) dungeon.getEntitiesOfType("mercenary").get(0);
        mercState = merc.getMovementStrategy().toString();

        assertTrue(playerState.equals("Invisible"));
        assertTrue(mercState.equals("Random"));

        dmc.tick(Direction.LEFT);
        playerState = dungeon.getPlayer().getPlayerState().toString();
        merc = (Mercenary) dungeon.getEntitiesOfType("mercenary").get(0);
        mercState = merc.getMovementStrategy().toString();

        assertTrue(playerState.equals("Alive"));
        assertTrue(mercState.equals("Follow"));
    }
}
