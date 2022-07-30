package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.collectableentities.InvincibilityPotion;
import dungeonmania.entities.collectableentities.InvisibilityPotion;
import dungeonmania.entities.movingentities.Player;
import dungeonmania.entities.movingentities.playerstates.AliveState;
import dungeonmania.entities.movingentities.playerstates.InvincibleState;
import dungeonmania.entities.movingentities.playerstates.InvisibleState;
import dungeonmania.util.Position;

public class PlayerStateTests {
        @Test
        @DisplayName("AliveState->InvisState")
        public void aliveToInvisState() {
                Player player = new Player(0, new Position(0, 0), 1, 1, 1, 1, 1, 1, 1, 1);
                InvisibilityPotion invis = new InvisibilityPotion(1, new Position(0, 0), 3);
                player.addItem(invis);
                assertTrue(player.getPlayerState() instanceof AliveState);
                player.drinkInvis(invis.getEntityId());
                assertTrue(player.getPlayerState() instanceof InvisibleState);
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof InvisibleState);
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof InvisibleState);
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof AliveState);
        }

        @Test
        @DisplayName("InvisState->InvinceState")
        public void invisToInvinceState() {
                Player player = new Player(0, new Position(0, 0), 1, 1, 1, 1, 1, 1, 1, 1);
                InvisibilityPotion invis = new InvisibilityPotion(1, new Position(0, 0), 3);
                InvincibilityPotion invin = new InvincibilityPotion(2, new Position(0, 0), 3);
                player.addItem(invis);
                player.addItem(invin);
                assertTrue(player.getPlayerState() instanceof AliveState);
                player.drinkInvis(invis.getEntityId());
                player.drinkInvinc(invin.getEntityId());
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof InvisibleState);
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof InvisibleState);
                assertTrue(player.getPlayerState() instanceof InvisibleState);
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof InvincibleState);
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof InvincibleState);
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof InvincibleState);
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof AliveState);
        }

        @Test
        @DisplayName("InvinceState->InvisState")
        public void invinceToInvisState() {
                Player player = new Player(0, new Position(0, 0), 1, 1, 1, 1, 1, 1, 1, 1);
                InvisibilityPotion invis = new InvisibilityPotion(1, new Position(0, 0), 3);
                InvincibilityPotion invin = new InvincibilityPotion(2, new Position(0, 0), 3);
                player.addItem(invin);
                player.addItem(invis);
                assertTrue(player.getPlayerState() instanceof AliveState);
                player.drinkInvinc(invin.getEntityId());
                player.drinkInvis(invis.getEntityId());
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof InvincibleState);
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof InvincibleState);
                assertTrue(player.getPlayerState() instanceof InvincibleState);
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof InvincibleState);
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof InvincibleState);
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof InvincibleState);
                player.tickPotion();
                assertTrue(player.getPlayerState() instanceof AliveState);
        }

        @Test
        @DisplayName("AliveState->DeadState")
        public void aliveToDeadState() {
                Player player = new Player(0, new Position(0, 0), 1, 1, 1, 1, 1, 1, 1, 1);
                assertTrue(player.getPlayerState() instanceof AliveState);
                player.loseHealth(-1);
                assertEquals(null, player.getPlayerState());
        }
}
