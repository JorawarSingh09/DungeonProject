package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.collectableentities.Treasure;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Position;

public class mercenaryTest {
    /**
     * Mercenaries do not spawn; they are only present if created as part of the
     * dungeon. They constantly move towards the Player, stopping only if they
     * cannot move any closer (they are able to move around walls). Mercenaries are
     * limited by the same movement constraints as the Player. All mercenaries are
     * considered hostile, unless the Player can bribe them with a certain amount of
     * gold; in which case they become allies. Mercenaries must be within a certain
     * radius of the player in order to be bribed, which is formed by the diagonally
     * and cardinally adjacent cells in a "square" fashion, akin to the blast radius
     * for bombs. As an ally, once it reaches the Player it simply follows the
     * Player around, occupying the square the player was previously in.
     */

    // test mercenart bribe
    @Test
    @DisplayName("test that a mercenary can be bribed")
    public void friendRequest() {

        Player player = new Player(1, new Position(0, 0),
                false, false, 1,
                1, 1, 1);
        Mercenary merc = new Mercenary(2, new Position(1, 0), true, false, 1, 1, 1, 1, 99, 1);

        player.addItem(new Treasure(3, new Position(0, 3), false, false));
        assertEquals(false, merc.isAllyToPlayer());
        player.attemptBribe(merc);
        assertEquals(true, merc.isAllyToPlayer());
    }
}
