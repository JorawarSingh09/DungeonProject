package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.movingentities.Assassin;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class InvisibilityAssassinTests {
    
    @Test
    @DisplayName("Player drinks invincibility potion and fights to win round 1")
    // should this not be an integration test
    public void invinceToAlive() throws InvalidActionException {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame(
                "invincibleAssassinTest", "playerState");
        Dungeon dungeon = dmc.dungeon;
        dungeon.getPlayer().addItem(new InvisibilityPotion(12,
                new Position(0, 0),
                2));

        String playerState = dungeon.getPlayer().getPlayerState().toString();
        Assassin ass = (Assassin) dungeon.getEntitiesOfType("assassin").get(0);
        String mercState = ass.getMovementStrategy().toString();

        assertTrue(playerState.equals("Alive"));
        assertTrue(mercState.equals("Follow"));

        dmc.tick("12");
        playerState = dungeon.getPlayer().getPlayerState().toString();
        ass = (Assassin) dungeon.getEntitiesOfType("assassin").get(0);
        mercState = ass.getMovementStrategy().toString();

        assertTrue(playerState.equals("Invisible"));
        assertTrue(mercState.equals("Random"));

        dmc.tick(Direction.LEFT);
        playerState = dungeon.getPlayer().getPlayerState().toString();
        ass = (Assassin) dungeon.getEntitiesOfType("assassin").get(0);
        mercState = ass.getMovementStrategy().toString();

        assertTrue(playerState.equals("Alive"));
        assertTrue(mercState.equals("Follow"));
    }

}
