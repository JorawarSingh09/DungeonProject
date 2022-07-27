package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.movingentities.Mercenary;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Position;

public class InvincibilityTests {

    @Test
    @DisplayName("Player drinks invincibility potion and fights to win round 1")
    public void aliveToInvincState() {
        Dungeon dungeon = new Dungeon("dungeon", 1);
        Player player = new Player(0, new Position(0, 0), false, false, 1, 1, 4, 4, 4, 1, 1, 1);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        player.addItem(new InvincibilityPotion(1, new Position(0, 0), false, false, 5));
        player.drinkInvinc(1);
        Mercenary merc = new Mercenary(2, new Position(0, 0), false, false, 100, 100, 100, 100, 100, 100);
        dungeon.addEntity(merc);
        dungeon.startBattle(merc);
        assertTrue(dungeon.getEntitiesOfType("player").size() == 1);
        assertTrue(dungeon.getEntitiesOfType("mercenary").size() == 0);
    }
}
