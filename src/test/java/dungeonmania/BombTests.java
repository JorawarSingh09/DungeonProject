package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.getInventory;;

public class BombTests {
    
    /**
     * 
     * Can be collected by the player. When removed from the inventory it is 
     * placed on the map at the player's location. When a bomb is cardinally 
     * adjacent to an active switch, it destroys all entities in diagonally 
     * and cardinally adjacent cells, except for the player, forming a "square" 
     * blast radius. The bomb should detonate when it is placed next to an already
     * active switch, or placed next to an inactive switch that then becomes active. 
     * The bomb explodes on the same tick it becomes cardinally adjacent to an active switch. 
     * A bomb cannot be picked up once it has been used.
     */

     @Test
     @DisplayName("Test the player can pickup a bomb")
     public void testBombPickup(){
        DungeonManiaController dmc  = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
            "d_simpleBomb", "bribe_radius_1");

        assertEquals(0, getInventory(res, "bomb").size());
        assertEquals(1, getEntities(res, "bomb").size());

        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, getInventory(res, "bomb").size());
        assertEquals(0, getEntities(res, "bomb").size());
     }

     @Test
     @DisplayName("Test the player can place a bomb")
     public void testBombPlace(){
        DungeonManiaController dmc  = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
            "d_simpleBomb", "bribe_radius_1");

        assertEquals(0, getInventory(res, "bomb").size());
        assertEquals(1, getEntities(res, "bomb").size());

        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, getInventory(res, "bomb").size());
        assertEquals(0, getEntities(res, "bomb").size());

        res = dmc.tick(Direction.RIGHT);
        assertDoesNotThrow(() -> dmc.tick("1"));

        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, getInventory(res, "bomb").size());
        assertEquals(1, getEntities(res, "bomb").size());
     }

     @Test
     @DisplayName("Test a bomb explodes adjacent to an already triggered switch")
     public void testAlreadyTriggeredExplosion(){
        DungeonManiaController dmc  = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
            "d_PlacedBombExplosion", "bribe_radius_1");

        // Move Boulder onto switch
        res = dmc.tick(Direction.RIGHT);
        //go up and pickup a bomb
        res = dmc.tick(Direction.UP);
        //go right and place bomb
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, getEntities(res, "switch").size());
        assertEquals(1, getEntities(res, "boulder").size());
        assertDoesNotThrow(() -> dmc.tick("3"));
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, getEntities(res, "switch").size());
        assertEquals(0, getEntities(res, "boulder").size());

     }

     @Test
     @DisplayName("Test a bomb explodes adjacent when bouulder pushed onto switch")
     public void testTriggeredExplosion(){
        DungeonManiaController dmc  = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
            "d_triggeredBombExplosion", "bribe_radius_1");

        // Move Boulder onto switch
        assertEquals(1, getEntities(res, "switch").size());
        assertEquals(1, getEntities(res, "boulder").size());
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, getEntities(res, "switch").size());
        assertEquals(0, getEntities(res, "boulder").size());

     }

     @Test
     @DisplayName("Test if a bomb kills an enemy when it explodes")
     public void testExplodeEnemy(){
        DungeonManiaController dmc  = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
            "d_mercenaryDeathByExplosion", "bribe_radius_1");

        assertEquals(1, getEntities(res, "switch").size());
        assertEquals(1, getEntities(res, "boulder").size());
        assertEquals(1, getEntities(res, "mercenary").size());

        res = dmc.tick(Direction.RIGHT);

        assertEquals(0, getEntities(res, "switch").size());
        assertEquals(0, getEntities(res, "boulder").size());
        assertEquals(0, getEntities(res, "mercenary").size());

        dmc.tick(Direction.UP);
        int kills = dmc.dungeon.getBattleController().getEnemiesKilled();
        assertEquals(1, kills);
        }
    
    @Test
    @DisplayName("Test if a bomb kills an ally when it explodes")
     public void testExplodeAlly(){
        DungeonManiaController dmc  = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
            "d_mercenaryDeathByExplosion", "c_mercenary_bribe_easy");

        assertEquals(1, getEntities(res, "switch").size());
        assertEquals(1, getEntities(res, "boulder").size());
        assertEquals(1, getEntities(res, "mercenary").size());
        
        assertDoesNotThrow(() -> dmc.interact("4"));
        res = dmc.tick(Direction.RIGHT);

        
        assertEquals(0, getEntities(res, "switch").size());
        assertEquals(0, getEntities(res, "boulder").size());
        assertEquals(0, getEntities(res, "mercenary").size());
        
        int kills = dmc.dungeon.getBattleController().getEnemiesKilled();
        assertEquals(0, kills);
        }
    
}
