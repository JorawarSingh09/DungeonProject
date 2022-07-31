package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class InvincibilityTests {

    @Test
    @DisplayName("Player drinks invincibility potion and fights to win round 1")
    // should this not be an integration test
    public void aliveToInvincState() throws InvalidActionException {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame(
                "invincibleTest", "playerState");
        Dungeon dungeon = dmc.dungeon;
        double initialPlayerHealth = dungeon.getPlayer().getHealth();
        dungeon.getPlayer().addItem(new InvincibilityPotion(12,
                new Position(0, 0),
                5));

        dmc.tick("12");
        assertTrue(dungeon.getEntitiesOfType("player").size() == 1);
        assertTrue(dungeon.getEntitiesOfType("mercenary").size() == 1);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        double newPlayerHealth = dungeon.getPlayer().getHealth();
        assertTrue(dungeon.getEntitiesOfType("player").size() == 1);
        assertTrue(dungeon.getEntitiesOfType("mercenary").size() == 0);
        assertEquals(initialPlayerHealth, newPlayerHealth);

    }
}
