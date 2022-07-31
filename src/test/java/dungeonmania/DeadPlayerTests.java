package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.playerstates.AliveState;
import dungeonmania.util.Position;

public class DeadPlayerTests {

    @Test
    @DisplayName("Dead player drinks potions")
    public void deadPotionDrink() {
        Dungeon dungeon = new Dungeon("nameOfDungeon", 1);
        Player player = new Player(0, new Position(1, 1), 10, 10, 10, 10, 10, 10, 10, 10);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        InvisibilityPotion invis = new InvisibilityPotion(1, new Position(0, 0), 3);
        InvincibilityPotion invin = new InvincibilityPotion(2, new Position(0, 0), 3);
        player.addItem(invin);
        player.addItem(invis);
        assertTrue(player.getPlayerState() instanceof AliveState);
        player.drinkInvinc(invin.getEntityId());
        player.drinkPotion(invis.getEntityId());
        player.tickPotion();
        player.setPlayerState(null);
        player.tickPotion();
        player.tickPotion();
        player.tickPotion();
    }

    @Test
    @DisplayName("Dead player incrrment potions")
    public void deadIncPotion() {
        Dungeon dungeon = new Dungeon("nameOfDungeon", 1);
        Player player = new Player(0, new Position(1, 1), 10, 10, 10, 10, 10, 10, 10, 10);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        InvisibilityPotion invis = new InvisibilityPotion(1, new Position(0, 0), 3);
        InvincibilityPotion invin = new InvincibilityPotion(2, new Position(0, 0), 3);
        player.addItem(invin);
        player.addItem(invis);
        player.setPlayerState(null);
        player.tickPotion();
        player.drinkInvinc(2);
        player.drinkInvis(1);
    }
    
}
