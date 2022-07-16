package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.util.Position;

public class PlayerStateTests {
    @Test
    @DisplayName("AliveState->InvisState")
    public void aliveToInvisState() {
        Player player = new Player(0, new Position(0, 0), false, false, 1, 1, 1, 1);
        InvisibilityPotion invis = new InvisibilityPotion(1, new Position(0, 0), false, false, 3);
        player.addItem(invis);
        assertEquals(player.getAliveState(), player.getPlayerState());
        player.drinkInvis(invis.getEntityId());
        assertEquals(player.getInvisState(), player.getPlayerState());
        player.tickPotion();
        assertEquals(player.getInvisState(), player.getPlayerState());
        player.tickPotion();
        assertEquals(player.getInvisState(), player.getPlayerState());
        player.tickPotion();
        assertEquals(player.getAliveState(), player.getPlayerState());
    }

    @Test
    @DisplayName("InvisState->InvinceState")
    public void invisToInvinceState() {
        Player player = new Player(0, new Position(0, 0), false, false, 1, 1, 1, 1);
        InvisibilityPotion invis = new InvisibilityPotion(1, new Position(0, 0), false, false, 3);
        InvincibilityPotion invin = new InvincibilityPotion(2, new Position(0, 0), false, false, 3);
        player.addItem(invis);
        player.addItem(invin);
        assertEquals(player.getAliveState(), player.getPlayerState());
        player.drinkInvis(invis.getEntityId());
        player.drinkInvinc(invin.getEntityId());
        assertEquals(player.getInvisState(), player.getPlayerState());
        player.tickPotion();
        assertEquals(player.getInvisState(), player.getPlayerState());
        assertEquals(player.getInvisState(), player.getPlayerState());
        player.tickPotion();
        assertEquals(player.getInvincState(), player.getPlayerState());
        player.tickPotion();
        assertEquals(player.getInvincState(), player.getPlayerState());
        player.tickPotion();
        assertEquals(player.getInvincState(), player.getPlayerState());
        player.tickPotion();
        assertEquals(player.getAliveState(), player.getPlayerState());
    }

    @Test
    @DisplayName("InvinceState->InvisState")
    public void invinceToInvisState() {
        Player player = new Player(0, new Position(0, 0), false, false, 1, 1, 1, 1);
        InvisibilityPotion invis = new InvisibilityPotion(1, new Position(0, 0), false, false, 3);
        InvincibilityPotion invin = new InvincibilityPotion(2, new Position(0, 0), false, false, 3);
        player.addItem(invin);
        player.addItem(invis);
        assertEquals(player.getAliveState(), player.getPlayerState());
        player.drinkInvinc(invin.getEntityId());
        player.drinkInvis(invis.getEntityId());
        assertEquals(player.getInvincState(), player.getPlayerState());
        player.tickPotion();
        assertEquals(player.getInvincState(), player.getPlayerState());
        assertEquals(player.getInvincState(), player.getPlayerState());
        player.tickPotion();
        assertEquals(player.getInvisState(), player.getPlayerState());
        player.tickPotion();
        assertEquals(player.getInvisState(), player.getPlayerState());
        player.tickPotion();
        assertEquals(player.getInvisState(), player.getPlayerState());
        player.tickPotion();
        assertEquals(player.getAliveState(), player.getPlayerState());
    }

    @Test
    @DisplayName("AliveState->DeadState")
    public void aliveToDeadState() {
        Player player = new Player(0, new Position(0, 0), false, false, 1, 1, 1, 1);
        assertEquals(player.getAliveState(), player.getPlayerState());
        player.loseHealth(-1);
        assertEquals(player.getDeadState(), player.getPlayerState());
    }
}
